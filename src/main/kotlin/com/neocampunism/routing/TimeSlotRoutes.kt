package com.neocampunism.routing

import com.neocampunism.model.TimeSlot
import com.neocampunism.response.ApiResponse
import com.neocampunism.service.time_slot.TimeSlotService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.timeSlotRoutes(timeSlotService: TimeSlotService) {
    route("/timeslots") {

        post {
            val timeSlot = call.receive<TimeSlot>()
            val response = timeSlotService.createTimeSlot(timeSlot)
            call.respond(HttpStatusCode.Created, response)
        }

        get {
            val response = timeSlotService.getTimeSlots()
            call.respond(HttpStatusCode.OK, response)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid time slot ID")
            )
            val response = timeSlotService.getTimeSlot(id)
            call.respond(HttpStatusCode.OK, response)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid time slot ID")
            )
            val updatedTimeSlot = call.receive<TimeSlot>()
            val response = timeSlotService.updateTimeSlot(id, updatedTimeSlot)
            call.respond(HttpStatusCode.OK, response)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid time slot ID")
            )
            val response = timeSlotService.deleteTimeSlot(id)
            call.respond(HttpStatusCode.OK, response)
        }
    }
}

