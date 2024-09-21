package com.neocampunism.plugins


import com.neocampunism.db.basex.BaseXDB
import com.neocampunism.db.sql.connectDBServer
import com.neocampunism.db.sql.createTables
import io.ktor.server.application.*
import io.ktor.server.config.*
import kotlinx.coroutines.launch

fun Application.configureDatabases(config: ApplicationConfig) {
    val type = config.property("storage.type").getString()
    val host = config.property("storage.host").getString()
    val port = config.property("storage.port").getString().toInt()

    val user = config.property("storage.user").getString()
    val password = config.property("storage.password").getString()
    val driver = config.property("storage.driverClassName").getString()
    val schema = config.property("storage.schema").getString()
    val url = "${config.property("storage.jdbcURL").getString()}$host:$port/$schema"

    when(type){
        "mysql" -> {
            launch {
                connectDBServer(
                    url = url,
                    user = user,
                    password = password,
                    driver = driver
                )

                createTables(schema)
            }
        }
        "postgresql" -> {

        }
        "basex" -> {
            launch {
                BaseXDB.start(
                    host = host,
                    port = port,
                    username = user,
                    password = password,
                    dbName = schema
                )
            }
        }
    }
}