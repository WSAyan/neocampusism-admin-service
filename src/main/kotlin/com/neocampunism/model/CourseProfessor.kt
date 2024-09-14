package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseProfessor(
    val courseProfessorID: Int? = null,
    val courseID: Int? = null,
    val professorID: Int? = null,
)