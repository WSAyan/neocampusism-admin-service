package com.neocampunism.service.semester

import com.neocampunism.model.Semester
import com.neocampunism.response.ApiResponse

interface SemesterService {
    suspend fun createSemester(semester: Semester): ApiResponse<Semester>
    suspend fun getSemesters(): ApiResponse<Map<String, List<Semester>>>
    suspend fun getSemester(id: Int): ApiResponse<Semester>
    suspend fun updateSemester(id: Int, semester: Semester): ApiResponse<Semester>
    suspend fun deleteSemester(id: Int): ApiResponse<Semester>
}