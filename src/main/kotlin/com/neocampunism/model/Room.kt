package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val roomID: Int?= null,
    val roomNumber: String?= null,
    val capacity: Int?= null,
    val roomType: RoomType?= null,
)
