package com.neocampunism.db.dao

import com.neocampunism.db.TimeSlots
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TimeSlotDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TimeSlotDao>(TimeSlots)
    var timeSlotID by TimeSlots.timeSlotID
    var dayOfWeek by TimeSlots.dayOfWeek
    var startTime by TimeSlots.startTime
    var endTime by TimeSlots.endTime
}