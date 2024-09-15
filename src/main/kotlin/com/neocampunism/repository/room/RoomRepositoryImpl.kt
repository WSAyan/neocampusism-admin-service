package com.neocampunism.repository.room

import com.neocampunism.db.*
import com.neocampunism.db.dao.ProfessorDao
import com.neocampunism.db.dao.RoomDao
import com.neocampunism.db.dao.daoToModel
import com.neocampunism.model.Room
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class RoomRepositoryImpl : RoomRepository {
    override suspend fun addRoom(room: Room): Room? = suspendTransaction {
        room.roomNumber ?: return@suspendTransaction null

        room.roomType ?: return@suspendTransaction null

        room.capacity ?: return@suspendTransaction null

        val newRoom = RoomDao.new {
            roomNumber = room.roomNumber
            roomType = RoomType.valueOf(room.roomType.name)
            capacity = room.capacity
        }

        daoToModel(newRoom)
    }

    override suspend fun getAllRooms(): List<Room> = suspendTransaction {
        RoomDao.all().map(::daoToModel)
    }

    override suspend fun getRoomById(id: Int): Room? = suspendTransaction {
        RoomDao
            .find {
                Rooms.id eq id
            }
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun updateRoom(id: Int, room: Room): Room? = suspendTransaction {
        room.roomNumber ?: return@suspendTransaction null

        room.roomType ?: return@suspendTransaction null

        room.capacity ?: return@suspendTransaction null

        val updatedRoom = RoomDao.findSingleByAndUpdate(Rooms.id eq id)  {
            it.roomNumber = room.roomNumber
            it.roomType = RoomType.valueOf(room.roomType.name)
            it.capacity = room.capacity
        } ?: return@suspendTransaction null

        daoToModel(updatedRoom)
    }

    override suspend fun deleteRoom(id: Int): Boolean = suspendTransaction {
        Rooms.deleteWhere { Rooms.id eq id } == 1
    }
}