package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseProfessor(
    val courseProfessorID: Int? = null,
    val course: Course? = null,
    val professor: Professor? = null,
)