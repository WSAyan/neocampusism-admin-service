package com.neocampunism.repository.time_slot

import com.neocampunism.model.TimeSlot

class FakeTimeSlotRepository : TimeSlotRepository {
    private val timeSlots = mutableListOf<TimeSlot>()
    private var nextId = 1

    override suspend fun addTimeSlot(timeSlot: TimeSlot): TimeSlot? {
        timeSlots.add(timeSlot.copy(timeSlotID = nextId++))
        return timeSlots.lastOrNull()
    }

    override suspend fun getAllTimeSlots(): List<TimeSlot> {
        return timeSlots.toList()
    }

    override suspend fun getTimeSlotById(id: Int): TimeSlot? {
        return timeSlots.find { it.timeSlotID == id }
    }

    override suspend fun updateTimeSlot(id: Int, timeSlot: TimeSlot): TimeSlot? {
        return timeSlots.firstOrNull { it.timeSlotID == id }
    }

    override suspend fun deleteTimeSlot(id: Int): Boolean {
        return timeSlots.removeIf { it.timeSlotID == id }
    }
}
