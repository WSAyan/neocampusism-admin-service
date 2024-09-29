package com.neocampunism

import com.neocampunism.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureExceptionHandling()
    configureSecurity()
    configureSerialization()
    configureRouting()
    configureMySql(environment.config)
    configureBaseX(environment.config)
}
