package com.neocampunism.repository.time_slot

import com.neocampunism.model.TimeSlot

class FakeTimeSlotRepository : TimeSlotRepository {
    private val timeSlots = mutableListOf<TimeSlot>()
    private var nextId = 1

    override suspend fun addTimeSlot(timeSlot: TimeSlot): Boolean {
        timeSlots.add(timeSlot.copy(timeSlotID = nextId++))
        return true
    }

    override suspend fun getAllTimeSlots(): List<TimeSlot> {
        return timeSlots.toList()
    }

    override suspend fun getTimeSlotById(id: Int): TimeSlot? {
        return timeSlots.find { it.timeSlotID == id }
    }

    override suspend fun updateTimeSlot(id: Int, timeSlot: TimeSlot): Boolean {
        val index = timeSlots.indexOfFirst { it.timeSlotID == id }
        if (index != -1) {
            timeSlots[index] = timeSlot.copy(timeSlotID = id)
            return true
        }
        return false
    }

    override suspend fun deleteTimeSlot(id: Int): Boolean {
        return timeSlots.removeIf { it.timeSlotID == id }
    }
}
