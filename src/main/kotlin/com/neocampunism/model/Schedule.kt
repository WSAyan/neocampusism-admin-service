package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val scheduleID: Int?= null,
    val courseID: Int?= null,
    val timeSlotID: Int?= null,
    val roomID: Int?= null,
)
