package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val courseID: Int? = null,
    val courseName: String? = null,
    val courseCode: String? = null,
    val departmentID: Int? = null,
    val credits: Int? = null,
    val courseType: CourseType? = null,
)