package com.neocampunism.db

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)

fun connectDBServer(
    url: String,
    driver: String,
    user: String,
    password: String,
    schema: String = ""
) =
    Database.connect(
        url = "$url/$schema",
        driver = driver,
        user = user,
        password = password,
    )

suspend fun createSchema(schema: String) {
    suspendTransaction {
        SchemaUtils.createSchema(Schema(schema))
    }
}

suspend fun createTables(schema: String) {
    suspendTransaction {
        SchemaUtils.setSchema(Schema(schema))

        SchemaUtils.create(
            Departments,
            Professors,
            Semesters,
            Courses,
            CourseProfessors,
            SemestersCourses,
            Rooms,
            TimeSlots,
            Schedules
        )
    }
}