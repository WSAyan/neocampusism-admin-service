package com.neocampunism.service.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.neocampunism.model.LoginData
import com.neocampunism.model.User
import com.neocampunism.response.ApiResponse
import com.neocampunism.utils.hashPassword
import com.neocampunism.utils.verifyPassword
import io.ktor.server.application.*
import java.util.*

class AuthServiceImpl(private val application: Application) : AuthService {
    private val jwtSecret = application.environment.config.property("jwt.secret").getString()
    private val jwtRefreshSecret = application.environment.config.property("jwt.refreshSecret").getString()
    private val jwtIssuer = application.environment.config.property("jwt.issuer").getString()
    private val jwtAudience = application.environment.config.property("jwt.audience").getString()


    override suspend fun register(email: String, password: String): ApiResponse<LoginData> {
        password.hashPassword()


        return login(email, password)
    }

    override suspend fun login(email: String, password: String): ApiResponse<LoginData> {
        // TODO: fetch user from db with email
        val user = User(email = email)

        if (!password.verifyPassword("hashed_password")) {
            return ApiResponse(
                status = "failed",
                message = "Wrong email or password",
            )
        }

        return ApiResponse(
            status = "success",
            message = "Login successful",
            data = LoginData(
                accessToken = generateToken(email),
                refreshToken = generateRefreshToken(email),
                expiresIn = 60000,
                user = user
            )
        )
    }

    private fun generateToken(email: String): String {
        val expiresAt = Date(System.currentTimeMillis() + 60000)

        val token = JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim("email", email)
            .withExpiresAt(expiresAt)
            .sign(Algorithm.HMAC256(jwtSecret))

        return token
    }

    private fun generateRefreshToken(email: String): String {
        val expiresAt = Date(System.currentTimeMillis() + 30L * 24L * 60L * 60L * 1000L)
        return JWT.create()
            .withAudience(jwtAudience)
            .withIssuer(jwtIssuer)
            .withClaim("email", email)
            .withExpiresAt(expiresAt)
            .sign(Algorithm.HMAC256(jwtRefreshSecret))
    }
}