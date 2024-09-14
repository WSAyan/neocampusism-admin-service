package com.neocampunism.routing

import com.neocampunism.model.Department
import com.neocampunism.response.ApiResponse
import com.neocampunism.service.department.DepartmentService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.departmentRoutes(departmentService: DepartmentService) {
    route("/departments") {

        post {
            val department = call.receive<Department>()
            val response = departmentService.createDepartment(department)
            call.respond(HttpStatusCode.Created, response)
        }

        get {
            val response = departmentService.getDepartments()
            call.respond(HttpStatusCode.OK, response)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid department ID")
            )

            val response = departmentService.getDepartment(id)
            call.respond(HttpStatusCode.OK, response)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid department ID")
            )

            val updatedDepartment = call.receive<Department>()
            val response = departmentService.updateDepartment(id, updatedDepartment)
            call.respond(HttpStatusCode.OK, response)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid department ID")
            )

            val response = departmentService.deleteDepartment(id)
            call.respond(HttpStatusCode.OK, response)
        }
    }
}

