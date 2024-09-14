package com.neocampunism.repository.course

import com.neocampunism.model.Course

class FakeCourseRepository : CourseRepository {
    private val courses = mutableListOf<Course>()
    private var nextId = 1

    override suspend fun addCourse(course: Course): Boolean {
        courses.add(course.copy(courseID = nextId++))
        return true
    }

    override suspend fun getAllCourses(): List<Course> {
        return courses.toList()
    }

    override suspend fun getCourseById(id: Int): Course? {
        return courses.find { it.courseID == id }
    }

    override suspend fun updateCourse(id: Int, course: Course): Boolean {
        val index = courses.indexOfFirst { it.courseID == id }
        if (index != -1) {
            courses[index] = course.copy(courseID = id)
            return true
        }
        return false
    }

    override suspend fun deleteCourse(id: Int): Boolean {
        return courses.removeIf { it.courseID == id }
    }
}
