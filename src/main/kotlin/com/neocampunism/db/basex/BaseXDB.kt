package com.neocampunism.db.basex

import org.basex.BaseXServer
import org.basex.api.client.ClientSession
import org.basex.core.cmd.CreateDB

object BaseXDB {
    lateinit var clientSession: ClientSession
    private lateinit var basexServer: BaseXServer

    fun start(
        host: String,
        port: Int,
        username: String,
        password: String,
        dbName: String
    ) {
        try {
            basexServer = BaseXServer()
            clientSession = ClientSession(host, port, username, password)
            clientSession.execute(CreateDB(dbName, "src/main/resources/xml/$dbName.xml"))
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun stop() {
        basexServer.stop()
    }
}



