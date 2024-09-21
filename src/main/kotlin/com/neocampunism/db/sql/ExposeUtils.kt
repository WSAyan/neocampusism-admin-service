package com.neocampunism.db.sql

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)

suspend fun connectDBServer(
    url: String,
    driver: String,
    user: String,
    password: String,
) : Boolean {
    repeat(5) { attempt ->
        println("connecting to database $url.......")

        try {
            Database.connect(
                url = url,
                driver = driver,
                user = user,
                password = password,
            )

            println("Database connected successfully.")

            return true
        } catch (e: Exception) {
            println("Database connection failed. Attempt ${attempt + 1} of 5.")
            if (attempt + 1 == 5) {
                println("Max retries reached. Cannot connect to the database.")
                return false
            }
            delay(500L)
        }
    }
    return false
}

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