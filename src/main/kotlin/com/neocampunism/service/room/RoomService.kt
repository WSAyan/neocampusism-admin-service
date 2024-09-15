package com.neocampunism.service.room

import com.neocampunism.model.Room
import com.neocampunism.response.ApiResponse

interface RoomService {
    suspend fun createRoom(room: Room): ApiResponse<Room>
    suspend fun getRooms(): ApiResponse<Map<String, List<Room>>>
    suspend fun getRoom(id: Int): ApiResponse<Room>
    suspend fun updateRoom(id: Int, room: Room): ApiResponse<Room>
    suspend fun deleteRoom(id: Int): ApiResponse<Room>
}
