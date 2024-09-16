package com.neocampunism.repository.semester_course


import com.neocampunism.model.SemesterCourse

interface SemestersCoursesRepository {
    suspend fun addSemesterCourse(semesterCourse: SemesterCourse): SemesterCourse?
    suspend fun getAllSemesterCourses(): List<SemesterCourse>
    suspend fun getSemesterCourseById(id: Int): SemesterCourse?
    suspend fun updateSemesterCourse(id: Int, semesterCourse: SemesterCourse): SemesterCourse?
    suspend fun deleteSemesterCourse(id: Int): Boolean
}