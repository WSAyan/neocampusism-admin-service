package com.neocampunism.routing

import com.campunix.Gene
import com.campunix.RoutineGenerator.RoutineGeneratorBuilder
import com.neocampunism.response.ApiResponse
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.routineGeneratorRoutes() {
    route("/routines") {

        post("/generate") {
            val genes: List<Gene> = listOf(
                Gene("CSE-203", "EI", "2-1", 1, true),
                Gene("CSE-205", "NAR", "2-1", 1, false),
                Gene("CSE-206", "GM", "2-1", 1, false),
                Gene("CSE-207", "MMB", "2-1", 1, false),
                Gene("CSE-208", "MZR", "2-1", 1, false),
                Gene("CSE-209", "MAI", "2-1", 1, false),
                Gene("CSE-210", "MAI", "2-1", 1, true),
                Gene("CSE-212", "EI", "2-1", 1, true),
                Gene("CSE-303", "SKS", "3-1", 2, false),
                Gene("CSE-305", "BA", "3-1", 2, false),
                Gene("CSE-307", "JKD", "3-1", 2, false),
                Gene("CSE-309", "AKA", "3-1", 2, false),
                Gene("CSE-314", "SB", "3-1", 2, true),
                Gene("CSE-304", "SKS", "3-1", 2, true)
            )
            val generator = RoutineGeneratorBuilder()
                .setAvailableGenes(genes)
                .setTotalPopulation(10)
                .build()

            generator.generate()

            call.respond(
                HttpStatusCode.OK,
                ApiResponse(
                    status = "success",
                    message = "Room created",
                    data = "yey"
                )
            )
        }
    }
}