package com.neocampunism.repository.room

import com.neocampunism.model.Room

class FakeRoomRepository : RoomRepository {
    private val rooms = mutableListOf<Room>()
    private var nextId = 1

    override suspend fun addRoom(room: Room): Room? {
        rooms.add(room.copy(roomID = nextId++))
        return rooms.lastOrNull()
    }

    override suspend fun getAllRooms(): List<Room> {
        return rooms.toList()
    }

    override suspend fun getRoomById(id: Int): Room? {
        return rooms.find { it.roomID == id }
    }

    override suspend fun updateRoom(id: Int, room: Room): Room? {
        return rooms.firstOrNull { it.roomID == id }
    }

    override suspend fun deleteRoom(id: Int): Boolean {
        return rooms.removeIf { it.roomID == id }
    }
}
