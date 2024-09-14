package com.neocampunism.service.course

import com.neocampunism.model.Course
import com.neocampunism.repository.course.CourseRepository
import com.neocampunism.response.ApiResponse

class CourseServiceImpl(private val courseRepository: CourseRepository) : CourseService {
    override suspend fun createCourse(course: Course): ApiResponse<Course> {
        return if (courseRepository.addCourse(course)) {
            ApiResponse(status = "success", message = "Course created", data = course)
        } else {
            ApiResponse(status = "failed", message = "Failed to create course")
        }
    }

    override suspend fun getCourses(): ApiResponse<Map<String, List<Course>>> {
        val courses = courseRepository.getAllCourses()
        return ApiResponse(
            status = "success",
            message = "Courses retrieved",
            data = mapOf("courses" to courses)
        )
    }

    override suspend fun getCourse(id: Int): ApiResponse<Course> {
        val course = courseRepository.getCourseById(id)
        return if (course != null) {
            ApiResponse(status = "success", message = "Course retrieved", data = course)
        } else {
            ApiResponse(status = "failed", message = "Course not found")
        }
    }

    override suspend fun updateCourse(id: Int, course: Course): ApiResponse<Course> {
        return if (courseRepository.updateCourse(id, course)) {
            ApiResponse(status = "success", message = "Course updated", data = course)
        } else {
            ApiResponse(status = "failed", message = "Failed to update course")
        }
    }

    override suspend fun deleteCourse(id: Int): ApiResponse<Any> {
        return if (courseRepository.deleteCourse(id)) {
            ApiResponse(status = "success", message = "Course deleted")
        } else {
            ApiResponse(status = "failed", message = "Failed to delete course")
        }
    }
}
