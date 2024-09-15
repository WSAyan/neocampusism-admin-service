package com.neocampunism.repository.semester

import com.neocampunism.db.Courses
import com.neocampunism.db.Semesters
import com.neocampunism.db.SemestersCourses
import com.neocampunism.db.dao.CourseDao
import com.neocampunism.db.dao.SemesterDao
import com.neocampunism.db.dao.daoToModel
import com.neocampunism.db.suspendTransaction
import com.neocampunism.model.Semester
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class SemesterRepositoryImpl : SemesterRepository {
    override suspend fun addSemester(semester: Semester): Semester? = suspendTransaction {
        semester.semesterCode ?: return@suspendTransaction null
        semester.semesterName ?: return@suspendTransaction null

        val newSemester = SemesterDao.new {
            semesterName = semester.semesterName
            semesterCode = semester.semesterCode
        }

        daoToModel(newSemester)
    }

    override suspend fun getAllSemesters(): List<Semester> = suspendTransaction {
        SemesterDao.all().map(::daoToModel)
    }

    override suspend fun getSemesterById(id: Int): Semester? = suspendTransaction {
        SemesterDao
            .find {
                Courses.id eq id
            }
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun updateSemester(id: Int, semester: Semester): Semester? = suspendTransaction {
        semester.semesterCode ?: return@suspendTransaction null
        semester.semesterName ?: return@suspendTransaction null

        val updatedSemester = SemesterDao.findSingleByAndUpdate(Semesters.id eq id) {
            it.semesterName = semester.semesterName
            it.semesterCode = semester.semesterCode
        } ?: return@suspendTransaction null

        daoToModel(updatedSemester)
    }

    override suspend fun deleteSemester(id: Int): Boolean = suspendTransaction {
        SemestersCourses.deleteWhere { semesterID eq id }

        Semesters.deleteWhere { Semesters.id eq id } == 1
    }
}