package com.neocampunism.plugins

import com.neocampunism.repository.course.CourseRepositoryImpl
import com.neocampunism.repository.department.DepartmentRepositoryImpl
import com.neocampunism.repository.professor.ProfessorRepositoryImpl
import com.neocampunism.repository.room.RoomRepositoryImpl
import com.neocampunism.repository.semester.SemesterRepositoryImpl
import com.neocampunism.repository.semester_course.SemestersCoursesRepositoryImpl
import com.neocampunism.routing.*
import com.neocampunism.response.ApiResponse
import com.neocampunism.response.ErrorBody
import com.neocampunism.service.course.CourseServiceImpl
import com.neocampunism.service.department.DepartmentServiceImpl
import com.neocampunism.service.professor.ProfessorServiceImpl
import com.neocampunism.service.room.RoomServiceImpl
import com.neocampunism.service.semester.SemesterServiceImpl
import com.neocampunism.service.semester_course.SemesterCourseServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        install(StatusPages) {
            exception<Throwable> { call, cause ->
                when (cause) {
                    is NotFoundException -> {
                        call.respond(
                            HttpStatusCode.NotFound,
                            ApiResponse(
                                status = "failed",
                                message = "Resource not found",
                                data = ErrorBody(cause = cause.localizedMessage)
                            )
                        )
                    }

                    is BadRequestException -> {
                        call.respond(
                            HttpStatusCode.BadRequest, ApiResponse(
                                status = "failed",
                                message = "Invalid request",
                                data = ErrorBody(cause = cause.localizedMessage)
                            )
                        )
                    }

                    else -> {
                        call.respond(
                            HttpStatusCode.InternalServerError,
                            ApiResponse(
                                status = "failed",
                                message = "An unexpected error occurred",
                                data = ErrorBody(cause = cause.localizedMessage)
                            )
                        )

                        logError(call, cause)
                    }
                }
            }
        }

        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")

        departmentRoutes(DepartmentServiceImpl(DepartmentRepositoryImpl()))
        professorRoutes(ProfessorServiceImpl(ProfessorRepositoryImpl()))
        courseRoutes(CourseServiceImpl(CourseRepositoryImpl()))
        semesterRoutes(SemesterServiceImpl(SemesterRepositoryImpl()))
        roomRoutes(RoomServiceImpl(RoomRepositoryImpl()))
        semesterCourseRoutes(SemesterCourseServiceImpl(SemestersCoursesRepositoryImpl()))
    }
}
