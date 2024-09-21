package com.neocampunism.db.basex

import com.neocampunism.model.Course
import com.neocampunism.model.CourseType
import nl.adaptivity.xmlutil.serialization.XML
import org.basex.BaseXServer
import org.basex.api.client.ClientSession
import org.basex.core.cmd.CreateDB
import java.io.IOException


object BaseXDB {
    private lateinit var clientSession: ClientSession
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
            testXml()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun stop() {
        clientSession.close()
        basexServer.stop()
    }

    fun testXml() {
        val course = Course(
            courseID = 101,
            courseName = "Computer Science 101",
            courseCode = "CS101",
            departmentID = 10,
            credits = 3,
            courseType = CourseType.Theory
        )

        // Serialize to XML
        val xmlFormat = XML { indentString = "  " }
        val courseXml = xmlFormat.encodeToString(Course.serializer(), course)
        println(courseXml)

        // Deserialize from XML
        val deserializedCourse = xmlFormat.decodeFromString(Course.serializer(), courseXml)
        println(deserializedCourse)
    }

    fun read(xQuery: String): String? {
        var result: String? = null
        try {
            result = clientSession.execute("XQUERY $xQuery")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return result
    }

    fun write(xQuery: String) {
        try {
            clientSession.execute("XQUERY $xQuery")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}



