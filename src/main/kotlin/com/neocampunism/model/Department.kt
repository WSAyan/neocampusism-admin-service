package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class Department(
    val departmentID: Int? = null,
    val departmentName: String? = null,
    val departmentCode: String? = null,
)