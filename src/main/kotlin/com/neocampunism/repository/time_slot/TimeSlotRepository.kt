package com.neocampunism.repository.time_slot

import com.neocampunism.model.TimeSlot

interface TimeSlotRepository {
    suspend fun addTimeSlot(timeSlot: TimeSlot): Boolean
    suspend fun getAllTimeSlots(): List<TimeSlot>
    suspend fun getTimeSlotById(id: Int): TimeSlot?
    suspend fun updateTimeSlot(id: Int, timeSlot: TimeSlot): Boolean
    suspend fun deleteTimeSlot(id: Int): Boolean
}
