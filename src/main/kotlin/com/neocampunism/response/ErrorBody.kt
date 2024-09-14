package com.neocampunism.response

data class ErrorBody(
    val cause: String? = null,
    val causes: List<String>? = null
)