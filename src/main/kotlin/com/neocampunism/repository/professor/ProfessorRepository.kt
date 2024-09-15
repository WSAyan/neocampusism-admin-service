package com.neocampunism.repository.professor

import com.neocampunism.model.Professor

interface ProfessorRepository {
    suspend fun addProfessor(professor: Professor): Professor?
    suspend fun getAllProfessors(): List<Professor>
    suspend fun getProfessorById(id: Int): Professor?
    suspend fun updateProfessor(id: Int, professor: Professor): Professor?
    suspend fun deleteProfessor(id: Int): Boolean
}
