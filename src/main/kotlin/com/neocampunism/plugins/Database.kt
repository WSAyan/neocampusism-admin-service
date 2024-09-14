package com.neocampunism.plugins


import com.neocampunism.db.SCHEMA_NAME
import com.neocampunism.db.connectDBServer
import com.neocampunism.db.createSchema
import com.neocampunism.db.createTables
import io.ktor.server.application.*
import kotlinx.coroutines.launch

fun Application.configureDatabases() {
    connectDBServer()

    launch {
        createSchema()
    }.invokeOnCompletion {
        connectDBServer(SCHEMA_NAME)

        launch {
            createTables()
        }
    }
}