package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val userName: String? = null,
    val email: String? = null,
    val phone: String? = null,
)