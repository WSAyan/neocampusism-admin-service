package com.neocampunism.repository.semester_course

import com.neocampunism.db.Courses
import com.neocampunism.db.Semesters
import com.neocampunism.db.SemestersCourses
import com.neocampunism.db.dao.SemestersCoursesDao
import com.neocampunism.db.dao.daoToModel
import com.neocampunism.db.suspendTransaction
import com.neocampunism.model.SemesterCourse
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class SemestersCoursesRepositoryImpl : SemestersCoursesRepository {
    override suspend fun addSemesterCourse(semesterCourse: SemesterCourse): SemesterCourse? = suspendTransaction {
        semesterCourse.semester?.semesterID ?: return@suspendTransaction null
        semesterCourse.course?.courseID ?: return@suspendTransaction null

        val newSemesterCourse = SemestersCoursesDao.new {
            semesterID = EntityID(semesterCourse.semester.semesterID, Semesters)
            courseID = EntityID(semesterCourse.course.courseID, Courses)
        }

        daoToModel(newSemesterCourse)
    }

    override suspend fun getAllSemesterCourses(): List<SemesterCourse> = suspendTransaction {
        SemestersCoursesDao.all().map(::daoToModel)
    }

    override suspend fun getSemesterCourseById(id: Int): SemesterCourse? = suspendTransaction {
        SemestersCoursesDao
            .find {
                SemestersCourses.id eq id
            }
            .map(::daoToModel)
            .firstOrNull()
    }
    
    override suspend fun updateSemesterCourse(id: Int, semesterCourse: SemesterCourse): SemesterCourse? =
        suspendTransaction {
            semesterCourse.semester?.semesterID ?: return@suspendTransaction null
            semesterCourse.course?.courseID ?: return@suspendTransaction null

            val updatedCourse = SemestersCoursesDao.findSingleByAndUpdate(SemestersCourses.id eq id) {
                it.semesterID = EntityID(semesterCourse.semester.semesterID, Semesters)
                it.courseID = EntityID(semesterCourse.course.courseID, Courses)
            } ?: return@suspendTransaction null

            daoToModel(updatedCourse)
        }

    override suspend fun deleteSemesterCourse(id: Int): Boolean = suspendTransaction {
        SemestersCourses.deleteWhere { SemestersCourses.id eq id } == 1
    }
}