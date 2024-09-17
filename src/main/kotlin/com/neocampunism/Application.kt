package com.neocampunism

import com.neocampunism.plugins.*
import io.ktor.server.application.*
/*import io.ktor.server.engine.*
import io.ktor.server.netty.**/

/*fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}*/

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureRouting()
    configureDatabases(environment.config)
}
