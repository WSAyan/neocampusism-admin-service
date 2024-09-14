package com.neocampunism.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureDatabases() {
    Database.connect(
        "jdbc:mysql://localhost:3306/UniversityRoutineDB",
        user = "root",
        password = ""
    )
}