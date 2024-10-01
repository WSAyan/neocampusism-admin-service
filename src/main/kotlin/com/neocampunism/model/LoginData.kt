package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginData(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val expiresIn: Long? = null,
    val tokenType: String? = null,
    val user: User? = null,
)
