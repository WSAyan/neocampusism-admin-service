package com.neocampunism.repository.room

import com.neocampunism.model.Room

interface RoomRepository {
    suspend fun addRoom(room: Room): Room?
    suspend fun getAllRooms(): List<Room>
    suspend fun getRoomById(id: Int): Room?
    suspend fun updateRoom(id: Int, room: Room): Room?
    suspend fun deleteRoom(id: Int): Boolean
}
