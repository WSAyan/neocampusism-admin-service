package com.neocampunism.service.auth

import com.neocampunism.model.LoginData
import com.neocampunism.model.User
import com.neocampunism.response.ApiResponse

interface AuthService {
    suspend fun register(email: String, password: String): ApiResponse<LoginData>
    suspend fun login(email: String, password: String): ApiResponse<LoginData>
}