package com.neocampunism.repository.room

import com.neocampunism.model.Room

class FakeRoomRepository : RoomRepository {
    private val rooms = mutableListOf<Room>()
    private var nextId = 1

    override suspend fun addRoom(room: Room): Boolean {
        rooms.add(room.copy(roomID = nextId++))
        return true
    }

    override suspend fun getAllRooms(): List<Room> {
        return rooms.toList()
    }

    override suspend fun getRoomById(id: Int): Room? {
        return rooms.find { it.roomID == id }
    }

    override suspend fun updateRoom(id: Int, room: Room): Boolean {
        val index = rooms.indexOfFirst { it.roomID == id }
        if (index != -1) {
            rooms[index] = room.copy(roomID = id)
            return true
        }
        return false
    }

    override suspend fun deleteRoom(id: Int): Boolean {
        return rooms.removeIf { it.roomID == id }
    }
}
