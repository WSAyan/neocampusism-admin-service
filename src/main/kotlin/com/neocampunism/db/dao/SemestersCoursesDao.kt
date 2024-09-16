package com.neocampunism.db.dao

import com.neocampunism.db.Courses
import com.neocampunism.db.Semesters
import com.neocampunism.db.SemestersCourses
import com.neocampunism.db.suspendTransaction
import com.neocampunism.model.Course
import com.neocampunism.model.SemesterCourse
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class SemestersCoursesDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SemestersCoursesDao>(SemestersCourses)

    var semesterID by SemestersCourses.semesterID
    var courseID by SemestersCourses.courseID
}

fun daoToModel(dao: SemestersCoursesDao) = SemesterCourse(
    semesterCourseID = dao.id.value,
    semester = SemesterDao
        .find {
            Semesters.id eq dao.semesterID
        }
        .map {
            daoToModel(it)
        }
        .firstOrNull(),
    course = CourseDao
        .find {
            Courses.id eq dao.courseID
        }
        .map {
            daoToModel(it)
        }
        .firstOrNull(),
)