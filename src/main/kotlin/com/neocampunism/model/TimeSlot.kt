package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class TimeSlot(
    val timeSlotID: Int? = null,
    val dayOfWeek: DayOfWeek? = null,
    val startTime: String? = null,
    val endTime: String? = null,
)
