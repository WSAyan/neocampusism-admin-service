package com.neocampunism.db

import com.mysql.cj.jdbc.MysqlDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Schema
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.vendors.ForUpdateOption
import org.jetbrains.exposed.sql.vendors.MysqlDialect

suspend fun <T> suspendTransaction(block: Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)

const val SCHEMA_NAME = "nc_admin"
const val USER = "root"
const val PASSWORD = ""

fun connectDBServer(schema: String = "") = Database.connect(
    url = "jdbc:mysql://localhost:3306/$schema",
    driver = "com.mysql.cj.jdbc.Driver",
    user = USER,
    password = PASSWORD,
)

suspend fun createSchema() {
    suspendTransaction {
        SchemaUtils.createSchema(Schema(SCHEMA_NAME))
    }
}

suspend fun createTables() {
    suspendTransaction {
        SchemaUtils.setSchema(Schema(SCHEMA_NAME))

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