package com.neocampunism.repository.professor

import com.neocampunism.model.Professor

class FakeProfessorRepository : ProfessorRepository {
    private val professors = mutableListOf<Professor>()
    private var nextId = 1

    override suspend fun addProfessor(professor: Professor): Boolean {
        professors.add(professor.copy(professorID = nextId++))
        return true
    }

    override suspend fun getAllProfessors(): List<Professor> {
        return professors.toList()
    }

    override suspend fun getProfessorById(id: Int): Professor? {
        return professors.find { it.professorID == id }
    }

    override suspend fun updateProfessor(id: Int, professor: Professor): Boolean {
        val index = professors.indexOfFirst { it.professorID == id }
        if (index != -1) {
            professors[index] = professor.copy(professorID = id)
            return true
        }
        return false
    }

    override suspend fun deleteProfessor(id: Int): Boolean {
        return professors.removeIf { it.professorID == id }
    }
}
