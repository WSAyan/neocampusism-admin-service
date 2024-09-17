package com.neocampunism.db.basex

import org.basex.BaseXServer
import org.basex.api.client.ClientSession
import org.basex.core.cmd.CreateDB

object BaseXDB {
    lateinit var clientSession: ClientSession
    private lateinit var basexServer: BaseXServer
    private const val HOST = "localhost"
    private const val PORT = 1984
    private const val USERNAME = "admin"
    private const val PASSWORD = "admin"
    private const val DB_NAME = "routine-v0.0.0"

    fun start() {
        try {
            basexServer = BaseXServer()
            clientSession = ClientSession(HOST, PORT, USERNAME, PASSWORD)
            clientSession.execute(CreateDB(DB_NAME, "src/main/resources/xml/$DB_NAME.xml"))
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun stop() {
        basexServer.stop()
    }
}



