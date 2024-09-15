package com.neocampunism.service.time_slot

import com.neocampunism.model.TimeSlot
import com.neocampunism.response.ApiResponse

interface TimeSlotService {
    suspend fun createTimeSlot(timeSlot: TimeSlot): ApiResponse<TimeSlot>
    suspend fun getTimeSlots(): ApiResponse<Map<String, List<TimeSlot>>>
    suspend fun getTimeSlot(id: Int): ApiResponse<TimeSlot>
    suspend fun updateTimeSlot(id: Int, timeSlot: TimeSlot): ApiResponse<TimeSlot>
    suspend fun deleteTimeSlot(id: Int): ApiResponse<TimeSlot>
}
