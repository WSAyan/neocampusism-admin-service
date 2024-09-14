package com.neocampunism.db.dao

import com.neocampunism.db.Schedules
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SchedulesDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SchedulesDao>(Schedules)
    var courseID by Schedules.courseID
    var timeSlotID by Schedules.timeSlotID
    var roomID by Schedules.roomID
}