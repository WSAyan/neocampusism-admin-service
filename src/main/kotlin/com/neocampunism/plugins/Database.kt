package com.neocampunism.plugins


import com.neocampunism.db.connectDBServer
import com.neocampunism.db.createSchema
import com.neocampunism.db.createTables
import io.ktor.server.application.*
import io.ktor.server.config.*
import kotlinx.coroutines.launch

fun Application.configureDatabases(config: ApplicationConfig) {
    val url = config.property("storage.jdbcURL").getString()
    val user = config.property("storage.user").getString()
    val password = config.property("storage.password").getString()
    val driver = config.property("storage.driverClassName").getString()
    val schema = config.property("storage.schema").getString()

    connectDBServer(
        url = url,
        user = user,
        password = password,
        driver = driver
    )

    launch {
        createSchema(schema)
    }.invokeOnCompletion {
        connectDBServer(
            url = url,
            user = user,
            password = password,
            driver = driver,
            schema = schema
        )

        launch {
            createTables(schema)
        }
    }
}