package com.neocampunism.service.semester_course

import com.neocampunism.model.SemesterCourse
import com.neocampunism.response.ApiResponse

interface SemesterCourseService {
    suspend fun createSemesterCourse(semesterCourse: SemesterCourse): ApiResponse<SemesterCourse>
    suspend fun getAllSemesterCourses(): ApiResponse<Map<String, List<SemesterCourse>>>
    suspend fun getAllSemesterCourses(id: Int): ApiResponse<SemesterCourse>
    suspend fun updateSemesterCourse(id: Int, semesterCourse: SemesterCourse): ApiResponse<SemesterCourse>
    suspend fun deleteSemesterCourse(id: Int): ApiResponse<SemesterCourse>
}
