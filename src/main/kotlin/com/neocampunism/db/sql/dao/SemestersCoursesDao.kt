package com.neocampunism.db.sql.dao

import com.neocampunism.db.sql.Courses
import com.neocampunism.db.sql.Semesters
import com.neocampunism.db.sql.SemestersCourses
import com.neocampunism.model.SemesterCourse
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

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