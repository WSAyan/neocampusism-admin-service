package com.neocampunism.response

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T? = null
)