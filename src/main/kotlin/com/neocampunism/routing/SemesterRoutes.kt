package com.neocampunism.routing

import com.neocampunism.model.Semester
import com.neocampunism.model.TimeSlot
import com.neocampunism.response.ApiResponse
import com.neocampunism.service.semester.SemesterService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.semesterRoutes(semesterService: SemesterService) {
    route("/semesters") {

        post {
            val semester = call.receive<Semester>()
            val response = semesterService.createSemester(semester)
            call.respond(HttpStatusCode.Created, response)
        }

        get {
            val response = semesterService.getSemesters()
            call.respond(HttpStatusCode.OK, response)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid semester ID")
            )
            val response = semesterService.getSemester(id)
            call.respond(HttpStatusCode.OK, response)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid semester ID")
            )
            val updatedSemester = call.receive<Semester>()
            val response = semesterService.updateSemester(id, updatedSemester)
            call.respond(HttpStatusCode.OK, response)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid semester ID")
            )
            val response = semesterService.deleteSemester(id)
            call.respond(HttpStatusCode.OK, response)
        }
    }
}

