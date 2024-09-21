package com.neocampunism.repository.course_professor

import com.neocampunism.db.sql.CourseProfessors
import com.neocampunism.db.sql.Courses
import com.neocampunism.db.sql.Professors
import com.neocampunism.db.sql.dao.CourseProfessorDao
import com.neocampunism.db.sql.dao.daoToModel
import com.neocampunism.db.sql.suspendTransaction
import com.neocampunism.model.CourseProfessor
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class CourseProfessorRepositoryImpl : CourseProfessorRepository {
    override suspend fun addCourseProfessor(courseProfessor: CourseProfessor): CourseProfessor? = suspendTransaction {
        courseProfessor.professor?.professorID ?: return@suspendTransaction null
        courseProfessor.course?.courseID ?: return@suspendTransaction null

        val newCourseProfessor = CourseProfessorDao.new {
            professorId = EntityID(courseProfessor.professor.professorID, Professors)
            courseID = EntityID(courseProfessor.course.courseID, Courses)
        }

        daoToModel(newCourseProfessor)
    }

    override suspend fun getAllCourseProfessors(): List<CourseProfessor> = suspendTransaction {
        CourseProfessorDao.all().map(::daoToModel)
    }

    override suspend fun getCourseProfessorById(id: Int): CourseProfessor? = suspendTransaction {
        CourseProfessorDao
            .find {
                CourseProfessors.id eq id
            }
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun updateCourseProfessor(id: Int, courseProfessor: CourseProfessor): CourseProfessor? =
        suspendTransaction {
            courseProfessor.professor?.professorID ?: return@suspendTransaction null
            courseProfessor.course?.courseID ?: return@suspendTransaction null

            val updatedCourseProfessor = CourseProfessorDao.findSingleByAndUpdate(CourseProfessors.id eq id) {
                it.professorId = EntityID(courseProfessor.professor.professorID, Professors)
                it.courseID = EntityID(courseProfessor.course.courseID, Courses)
            } ?: return@suspendTransaction null

            daoToModel(updatedCourseProfessor)
        }

    override suspend fun deleteCourseProfessor(id: Int): Boolean = suspendTransaction {
        CourseProfessors.deleteWhere { CourseProfessors.id eq id } == 1
    }
}