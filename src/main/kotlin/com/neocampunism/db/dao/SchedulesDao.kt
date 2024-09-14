package com.neocampunism.db.dao

import com.neocampunism.db.Schedules
import com.neocampunism.model.Schedule
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SchedulesDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SchedulesDao>(Schedules)

    var courseID by Schedules.courseID
    var timeSlotID by Schedules.timeSlotID
    var roomID by Schedules.roomID
}

fun daoToModel(dao: SchedulesDao) = Schedule(
    scheduleID = dao.id.value,
    courseID = dao.courseID.value,
    timeSlotID = dao.timeSlotID.value,
    roomID = dao.roomID.value,
)