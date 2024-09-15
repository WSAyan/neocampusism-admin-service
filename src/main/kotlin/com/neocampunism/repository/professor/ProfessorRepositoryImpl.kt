package com.neocampunism.repository.professor

import com.neocampunism.db.*
import com.neocampunism.db.dao.CourseDao
import com.neocampunism.db.dao.ProfessorDao
import com.neocampunism.db.dao.daoToModel
import com.neocampunism.model.Professor
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class ProfessorRepositoryImpl: ProfessorRepository {
    override suspend fun addProfessor(professor: Professor): Professor? = suspendTransaction {
        professor.firstName ?: return@suspendTransaction null

        professor.lastName  ?: return@suspendTransaction null

        professor.shortName  ?: return@suspendTransaction null

        professor.departmentID ?: return@suspendTransaction null

        professor.phone ?: return@suspendTransaction null

        professor.email ?: return@suspendTransaction null

        val newProfessor = ProfessorDao.new {
            firstName = professor.firstName
            lastName = professor.lastName
            shortName = professor.shortName
            email = professor.email
            departmentID = EntityID(professor.departmentID, Departments)
            phone = professor.phone
        }

        daoToModel(newProfessor)
    }

    override suspend fun getAllProfessors(): List<Professor> = suspendTransaction {
        ProfessorDao.all().map(::daoToModel)
    }

    override suspend fun getProfessorById(id: Int): Professor? = suspendTransaction {
        ProfessorDao
            .find {
                Courses.id eq id
            }
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun updateProfessor(id: Int, professor: Professor): Professor? = suspendTransaction {
        professor.firstName ?: return@suspendTransaction null

        professor.lastName  ?: return@suspendTransaction null

        professor.shortName  ?: return@suspendTransaction null

        professor.departmentID ?: return@suspendTransaction null

        professor.phone ?: return@suspendTransaction null

        professor.email ?: return@suspendTransaction null

        val updatedProfessor = ProfessorDao.findSingleByAndUpdate(Professors.id eq id) {
            it.firstName = professor.firstName
            it.lastName = professor.lastName
            it.shortName = professor.shortName
            it.email = professor.email
            it.departmentID = EntityID(professor.departmentID, Departments)
            it.phone = professor.phone
        } ?: return@suspendTransaction null

        daoToModel(updatedProfessor)
    }

    override suspend fun deleteProfessor(id: Int): Boolean = suspendTransaction {
        Courses.deleteWhere { Professors.id eq id } == 1
    }
}