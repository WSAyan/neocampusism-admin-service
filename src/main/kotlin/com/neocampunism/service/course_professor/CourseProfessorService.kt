package com.neocampunism.service.course_professor

import com.neocampunism.model.CourseProfessor
import com.neocampunism.response.ApiResponse

interface CourseProfessorService {
    suspend fun createCourseProfessor(courseProfessor: CourseProfessor): ApiResponse<CourseProfessor>
    suspend fun getCourseProfessors(): ApiResponse<Map<String, List<CourseProfessor>>>
    suspend fun getCourseProfessor(id: Int): ApiResponse<CourseProfessor>
    suspend fun updateCourseProfessor(id: Int, courseProfessor: CourseProfessor): ApiResponse<CourseProfessor>
    suspend fun deleteCourseProfessor(id: Int): ApiResponse<CourseProfessor>
}