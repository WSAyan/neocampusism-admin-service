package com.neocampunism.routing

import com.neocampunism.model.Professor
import com.neocampunism.response.ApiResponse
import com.neocampunism.service.professor.ProfessorService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.professorRoutes(professorService: ProfessorService) {
    route("/professors") {

        post {
            val professor = call.receive<Professor>()
            val response = professorService.createProfessor(professor)
            call.respond(HttpStatusCode.Created, response)
        }

        get {
            val response = professorService.getProfessors()
            call.respond(HttpStatusCode.OK, response)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid professor ID")
            )

            val response = professorService.getProfessor(id)
            call.respond(HttpStatusCode.OK, response)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid professor ID")
            )
            val updatedProfessor = call.receive<Professor>()
            val response = professorService.updateProfessor(id, updatedProfessor)
            call.respond(HttpStatusCode.OK, response)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid professor ID")
            )
            val response = professorService.deleteProfessor(id)
            call.respond(HttpStatusCode.OK, response)
        }
    }
}
