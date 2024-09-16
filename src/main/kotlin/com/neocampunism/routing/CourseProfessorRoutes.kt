package com.neocampunism.routing

import com.neocampunism.model.CourseProfessor
import com.neocampunism.response.ApiResponse
import com.neocampunism.service.course_professor.CourseProfessorService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.courseProfessorRoutes(courseProfessorService: CourseProfessorService) {
    route("/courseProfessors") {

        post {
            val courseProfessor = call.receive<CourseProfessor>()
            val response = courseProfessorService.createCourseProfessor(courseProfessor)
            call.respond(HttpStatusCode.Created, response)
        }

        get {
            val response = courseProfessorService.getCourseProfessors()
            call.respond(HttpStatusCode.OK, response)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid course professor ID")
            )
            val response = courseProfessorService.getCourseProfessor(id)
            call.respond(HttpStatusCode.OK, response)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid semester ID")
            )
            val courseProfessor = call.receive<CourseProfessor>()
            val response = courseProfessorService.updateCourseProfessor(id, courseProfessor)
            call.respond(HttpStatusCode.OK, response)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid semester ID")
            )
            val response = courseProfessorService.deleteCourseProfessor(id)
            call.respond(HttpStatusCode.OK, response)
        }
    }
}