package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class Semester(
    val semesterID: Int?= null,
    val semesterName: String?= null,
    val semesterCode: String?= null,
)
