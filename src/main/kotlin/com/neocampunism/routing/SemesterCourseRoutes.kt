package com.neocampunism.routing

import com.neocampunism.model.SemesterCourse
import com.neocampunism.response.ApiResponse
import com.neocampunism.service.semester_course.SemesterCourseService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.semesterCourseRoutes(semesterCourseService: SemesterCourseService) {

    route("/semesterCourses") {
        post {
            val semesterCourse = call.receive<SemesterCourse>()
            val response = semesterCourseService.createSemesterCourse(semesterCourse)
            call.respond(HttpStatusCode.Created, response)
        }

        get {
            val response = semesterCourseService.getAllSemesterCourses()
            call.respond(HttpStatusCode.OK, response)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid semester course ID")
            )
            val response = semesterCourseService.getAllSemesterCourses(id)
            call.respond(HttpStatusCode.OK, response)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid semester course ID")
            )
            val updatedSemesterCourse = call.receive<SemesterCourse>()
            val response = semesterCourseService.updateSemesterCourse(id, updatedSemesterCourse)
            call.respond(HttpStatusCode.OK, response)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid semester course ID")
            )
            val response = semesterCourseService.deleteSemesterCourse(id)
            call.respond(HttpStatusCode.OK, response)
        }
    }
}