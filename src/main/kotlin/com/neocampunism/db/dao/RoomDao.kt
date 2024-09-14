package com.neocampunism.db.dao

import com.neocampunism.db.Rooms
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RoomDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RoomDao>(Rooms)
    var roomId by Rooms.roomID
    var roomNumber by Rooms.roomNumber
    var roomType by Rooms.roomType
    var capacity by Rooms.capacity
}