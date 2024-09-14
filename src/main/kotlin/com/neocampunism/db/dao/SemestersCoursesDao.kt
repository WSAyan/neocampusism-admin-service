package com.neocampunism.db.dao

import com.neocampunism.db.Courses
import com.neocampunism.db.Semesters
import com.neocampunism.db.SemestersCourses
import com.neocampunism.model.SemesterCourse
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SemestersCoursesDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SemestersCoursesDao>(SemestersCourses)

    var semesterCourseID by Semesters.semesterID
    var semesterID by Semesters.semesterID
    var courseID by Courses.courseID
}

fun daoToModel(dao: SemestersCoursesDao) = SemesterCourse(
    semesterID = dao.semesterID,
    semesterCourseID = dao.semesterCourseID,
    courseID = dao.courseID,
)