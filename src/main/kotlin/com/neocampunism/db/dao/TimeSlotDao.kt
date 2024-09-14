package com.neocampunism.db.dao

import com.neocampunism.db.TimeSlots
import com.neocampunism.model.DayOfWeek
import com.neocampunism.model.TimeSlot
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TimeSlotDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TimeSlotDao>(TimeSlots)

    var dayOfWeek by TimeSlots.dayOfWeek
    var startTime by TimeSlots.startTime
    var endTime by TimeSlots.endTime
}

fun daoToModel(dao: TimeSlotDao) = TimeSlot(
    timeSlotID = dao.id.value,
    dayOfWeek = DayOfWeek.valueOf(dao.dayOfWeek.name),
    startTime = dao.startTime.toString(),
    endTime = dao.endTime.toString()
)