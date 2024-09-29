package com.neocampunism.plugins


import com.neocampunism.db.basex.BaseXDB
import com.neocampunism.db.connectDBServer
import com.neocampunism.db.createTables
import io.ktor.server.application.*
import io.ktor.server.config.*
import kotlinx.coroutines.launch

fun Application.configureMySql(config: ApplicationConfig) {
    val user = config.property("storageMySql.user").getString()
    val password = config.property("storageMySql.password").getString()
    val driver = config.property("storageMySql.driverClassName").getString()
    val schema = config.property("storageMySql.schema").getString()
    val url = config.property("storageMySql.jdbcURL").getString()

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

fun Application.configureBaseX(config: ApplicationConfig) {
    val user = config.property("storageBaseX.user").getString()
    val password = config.property("storageBaseX.password").getString()
    val host = config.property("storageBaseX.host").getString()
    val port = config.property("storageBaseX.port").getString().toInt()
    val db = config.property("storageBaseX.db").getString()

    launch {
        BaseXDB.start(
            host = host,
            port = port,
            username = user,
            password = password,
            dbName = db
        )
    }
}