package com.neocampunism.model

import kotlinx.serialization.Serializable

@Serializable
data class SemesterCourse(
    val semesterCourseID: Int?= null,
    val semester: Semester?= null,
    val course: Course?= null,
)