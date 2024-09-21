package com.neocampunism.db.sql.dao

import com.neocampunism.db.sql.Courses
import com.neocampunism.db.sql.Semesters
import com.neocampunism.db.sql.SemestersCourses
import com.neocampunism.model.Semester
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SemesterDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SemesterDao>(Semesters)

    var semesterName by Semesters.semesterName
    var semesterCode by Semesters.semesterCode
}

fun daoToModel(dao: SemesterDao) = Semester(
    semesterID = dao.id.value,
    semesterName = dao.semesterName,
    semesterCode = dao.semesterCode,
    courses = (SemestersCourses innerJoin Courses)
        .select(Courses.columns)
        .where { SemestersCourses.semesterID eq dao.id }
        .map { row ->
            daoToModel(CourseDao.wrapRow(row))
        }
)