package com.neocampunism.repository.professor

import com.neocampunism.model.Professor

class FakeProfessorRepository : ProfessorRepository {
    private val professors = mutableListOf<Professor>()
    private var nextId = 1

    override suspend fun addProfessor(professor: Professor): Professor? {
        professors.add(professor.copy(professorID = nextId++))
        return professors.lastOrNull()
    }

    override suspend fun getAllProfessors(): List<Professor> {
        return professors.toList()
    }

    override suspend fun getProfessorById(id: Int): Professor? {
        return professors.find { it.professorID == id }
    }

    override suspend fun updateProfessor(id: Int, professor: Professor): Professor? {
        return  professors.firstOrNull{ it.professorID == id }
    }

    override suspend fun deleteProfessor(id: Int): Boolean {
        return professors.removeIf { it.professorID == id }
    }
}
