package com.neocampunism.service.professor

import com.neocampunism.model.Professor
import com.neocampunism.response.ApiResponse

interface ProfessorService {
    suspend fun createProfessor(professor: Professor): ApiResponse<Professor>
    suspend fun getProfessors(): ApiResponse<Map<String, List<Professor>>>
    suspend fun getProfessor(id: Int): ApiResponse<Professor>
    suspend fun updateProfessor(id: Int, professor: Professor): ApiResponse<Professor>
    suspend fun deleteProfessor(id: Int): ApiResponse<Professor>
}
