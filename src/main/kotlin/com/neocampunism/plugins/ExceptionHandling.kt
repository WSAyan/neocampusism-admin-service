package com.neocampunism.plugins

import com.neocampunism.response.ApiResponse
import com.neocampunism.response.ErrorBody
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureExceptionHandling() {
    install(StatusPages) {

        exception<Throwable> { call, cause ->
            when (cause) {
                is NotFoundException -> {
                    call.respond(
                        HttpStatusCode.NotFound,
                        ApiResponse(
                            status = "failed",
                            message = "Resource not found",
                            data = ErrorBody(cause = cause.localizedMessage)
                        )
                    )
                }

                is BadRequestException -> {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        ApiResponse(
                            status = "failed",
                            message = "Invalid request",
                            data = ErrorBody(cause = cause.localizedMessage)
                        )
                    )
                }

                else -> {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        ApiResponse(
                            status = "failed",
                            message = "An unexpected error occurred",
                            data = ErrorBody(cause = cause.localizedMessage)
                        )
                    )

                    logError(call, cause)
                }
            }
        }


        status(HttpStatusCode.NotFound) { status ->
            call.respond(
                status,
                ApiResponse(
                    status = "failed",
                    message = "Resource not found",
                    data = ErrorBody(cause = status.description)
                )
            )
        }

        status(HttpStatusCode.Unauthorized) { status ->
            call.respond(
                status,
                ApiResponse(
                    status = "failed",
                    message = "Unauthorized",
                    data = ErrorBody(cause = status.description)
                )
            )
        }

        status(HttpStatusCode.MethodNotAllowed) { status ->
            call.respond(
                status,
                ApiResponse(
                    status = "failed",
                    message = "Method not allowed",
                    data = ErrorBody(cause = status.description)
                )
            )
        }
    }
}