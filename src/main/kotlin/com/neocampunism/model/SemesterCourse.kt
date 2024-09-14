package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class SemesterCourse(
    val semesterCourseID: Int?= null,
    val semesterID: Int?= null,
    val courseID: Int?= null,
)