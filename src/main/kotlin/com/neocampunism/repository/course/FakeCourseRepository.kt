package com.neocampunism.repository.course

import com.neocampunism.model.Course

class FakeCourseRepository : CourseRepository {
    private val courses = mutableListOf<Course>()
    private var nextId = 1

    override suspend fun addCourse(course: Course): Course? {
        courses.add(course.copy(courseID = nextId++))
        return courses.lastOrNull()
    }

    override suspend fun getAllCourses(): List<Course> {
        return courses.toList()
    }

    override suspend fun getCourseById(id: Int): Course? {
        return courses.find { it.courseID == id }
    }

    override suspend fun updateCourse(id: Int, course: Course): Course? {
        return  courses.firstOrNull { it.courseID == id }
    }

    override suspend fun deleteCourse(id: Int): Boolean {
        return courses.removeIf { it.courseID == id }
    }

    override suspend fun getAllCoursesBySemesterId(semesterId: Int): List<Course> {
        return listOf()
    }
}
