package com.neocampunism.routing

import com.neocampunism.model.Course
import com.neocampunism.response.ApiResponse
import com.neocampunism.service.course.CourseService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.courseRoutes(courseService: CourseService) {
    route("/courses") {

        post {
            val course = call.receive<Course>()
            val response = courseService.createCourse(course)
            call.respond(HttpStatusCode.Created, response)
        }

        get {
            val response = courseService.getCourses()
            call.respond(HttpStatusCode.OK, response)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid course ID")
            )
            val response = courseService.getCourse(id)
            call.respond(HttpStatusCode.OK, response)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid course ID")
            )
            val updatedCourse = call.receive<Course>()
            val response = courseService.updateCourse(id, updatedCourse)
            call.respond(HttpStatusCode.OK, response)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid course ID")
            )
            val response = courseService.deleteCourse(id)
            call.respond(HttpStatusCode.OK, response)
        }
    }
}


