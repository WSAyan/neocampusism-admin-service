package com.neocampunism.db.dao

import com.neocampunism.db.Schedules
import com.neocampunism.model.Schedule
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SchedulesDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SchedulesDao>(Schedules)
    var scheduleID by Schedules.scheduleID
    var courseID by Schedules.courseID
    var timeSlotID by Schedules.timeSlotID
    var roomID by Schedules.roomID
}

fun daoToModel(dao: SchedulesDao) = Schedule(
    scheduleID = dao.scheduleID,
    courseID = dao.courseID,
    timeSlotID = dao.timeSlotID,
    roomID = dao.roomID,
)