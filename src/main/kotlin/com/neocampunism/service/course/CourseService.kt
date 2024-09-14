package com.neocampunism.service.course

import com.neocampunism.model.Course
import com.neocampunism.response.ApiResponse

interface CourseService {
    suspend fun createCourse(course: Course): ApiResponse<Course>
    suspend fun getCourses(): ApiResponse<Map<String, List<Course>>>
    suspend fun getCourse(id: Int): ApiResponse<Course>
    suspend fun updateCourse(id: Int, course: Course): ApiResponse<Course>
    suspend fun deleteCourse(id: Int): ApiResponse<Any>
}
