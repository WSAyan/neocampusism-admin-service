package com.neocampunism.repository.course

import com.neocampunism.model.Course

interface CourseRepository {
    suspend fun addCourse(course: Course): Course?
    suspend fun getAllCourses(): List<Course>
    suspend fun getCourseById(id: Int): Course?
    suspend fun updateCourse(id: Int, course: Course): Course?
    suspend fun deleteCourse(id: Int): Boolean
    suspend fun getAllCoursesBySemesterId(semesterId: Int): List<Course>
}
