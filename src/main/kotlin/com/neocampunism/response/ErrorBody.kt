package com.neocampunism.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorBody(
    val cause: String? = null,
    val causes: List<String>? = null
)