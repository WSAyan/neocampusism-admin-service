package com.neocampunism.service.time_slot

import com.neocampunism.model.TimeSlot
import com.neocampunism.repository.time_slot.TimeSlotRepository
import com.neocampunism.response.ApiResponse

class TimeSlotServiceImpl(private val timeSlotRepository: TimeSlotRepository) : TimeSlotService {
    override suspend fun createTimeSlot(timeSlot: TimeSlot): ApiResponse<TimeSlot> {
        return if (timeSlotRepository.addTimeSlot(timeSlot)) {
            ApiResponse(status = "success", message = "Time Slot created", data = timeSlot)
        } else {
            ApiResponse(status = "failed", message = "Failed to create time slot")
        }
    }

    override suspend fun getTimeSlots(): ApiResponse<Map<String, List<TimeSlot>>> {
        val timeSlots = timeSlotRepository.getAllTimeSlots()
        return ApiResponse(
            status = "success",
            message = "Time Slots retrieved",
            data = mapOf("timeSlots" to timeSlots)
        )
    }

    override suspend fun getTimeSlot(id: Int): ApiResponse<TimeSlot> {
        val timeSlot = timeSlotRepository.getTimeSlotById(id)
        return if (timeSlot != null) {
            ApiResponse(status = "success", message = "Time Slot retrieved", data = timeSlot)
        } else {
            ApiResponse(status = "failed", message = "Time Slot not found")
        }
    }

    override suspend fun updateTimeSlot(id: Int, timeSlot: TimeSlot): ApiResponse<TimeSlot> {
        return if (timeSlotRepository.updateTimeSlot(id, timeSlot)) {
            ApiResponse(status = "success", message = "Time Slot updated", data = timeSlot)
        } else {
            ApiResponse(status = "failed", message = "Failed to update time slot")
        }
    }

    override suspend fun deleteTimeSlot(id: Int): ApiResponse<TimeSlot> {
        return if (timeSlotRepository.deleteTimeSlot(id)) {
            ApiResponse(status = "success", message = "Time Slot deleted")
        } else {
            ApiResponse(status = "failed", message = "Failed to delete time slot")
        }
    }
}
