package com.neocampunism.routing

import com.neocampunism.model.Room
import com.neocampunism.response.ApiResponse
import com.neocampunism.service.room.RoomService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.roomRoutes(roomService: RoomService) {
    route("/rooms") {

        post {
            val room = call.receive<Room>()
            val response = roomService.createRoom(room)
            call.respond(HttpStatusCode.Created, response)
        }

        get {
            val response = roomService.getRooms()
            call.respond(HttpStatusCode.OK, response)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid room ID")
            )
            val response = roomService.getRoom(id)
            call.respond(HttpStatusCode.OK, response)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid room ID")
            )
            val updatedRoom = call.receive<Room>()
            val response = roomService.updateRoom(id, updatedRoom)
            call.respond(HttpStatusCode.OK, response)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest,
                ApiResponse<Any>(status = "failed", message = "Invalid room ID")
            )
            val response = roomService.deleteRoom(id)
            call.respond(HttpStatusCode.OK, response)
        }
    }
}

