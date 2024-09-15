package com.neocampunism.service.room

import com.neocampunism.model.Room
import com.neocampunism.repository.room.RoomRepository
import com.neocampunism.response.ApiResponse

class RoomServiceImpl(private val roomRepository: RoomRepository) : RoomService {
    override suspend fun createRoom(room: Room): ApiResponse<Room> {
        return ApiResponse(
            status = "success",
            message = "Room created",
            data = roomRepository.addRoom(room)
                ?: return ApiResponse(
                    status = "failed",
                    message = "Failed to create room"
                )
        )
    }

    override suspend fun getRooms(): ApiResponse<Map<String, List<Room>>> {
        val rooms = roomRepository.getAllRooms()
        return ApiResponse(
            status = "success",
            message = "Rooms retrieved",
            data = mapOf("rooms" to rooms)
        )
    }

    override suspend fun getRoom(id: Int): ApiResponse<Room> {
        return ApiResponse(
            status = "success",
            message = "Room retrieved",
            data = roomRepository.getRoomById(id)
                ?: return ApiResponse(status = "failed", message = "Room not found")
        )
    }

    override suspend fun updateRoom(id: Int, room: Room): ApiResponse<Room> {
        return ApiResponse(
            status = "success",
            message = "Room updated",
            data = roomRepository.updateRoom(id, room)
                ?: return ApiResponse(
                    status = "failed",
                    message = "Failed to update room"
                )
        )
    }

    override suspend fun deleteRoom(id: Int): ApiResponse<Room> {
        return if (roomRepository.deleteRoom(id)) {
            ApiResponse(status = "success", message = "Room deleted")
        } else {
            ApiResponse(status = "failed", message = "Failed to delete room")
        }
    }
}
