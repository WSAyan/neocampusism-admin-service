package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class Professor(
    val professorID: Int?= null,
    val firstName: String?= null,
    val lastName: String?= null,
    val shortName: String?= null,
    val departmentID: Int?= null,
    val email: String?= null,
    val phone: String?= null,
)
