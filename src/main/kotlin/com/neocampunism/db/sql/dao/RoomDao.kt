package com.neocampunism.db.sql.dao

import com.neocampunism.db.sql.Rooms
import com.neocampunism.model.Room
import com.neocampunism.model.RoomType
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RoomDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RoomDao>(Rooms)

    var roomNumber by Rooms.roomNumber
    var roomType by Rooms.roomType
    var capacity by Rooms.capacity
}

fun daoToModel(roomDao: RoomDao) = Room(
    roomID = roomDao.id.value,
    roomNumber = roomDao.roomNumber,
    capacity = roomDao.capacity,
    roomType = RoomType.valueOf(roomDao.roomType.name)
)