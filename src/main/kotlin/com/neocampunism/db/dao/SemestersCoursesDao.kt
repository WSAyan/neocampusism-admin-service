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

    var semesterID by SemestersCourses.semesterID
    var courseID by SemestersCourses.courseID
}

fun daoToModel(dao: SemestersCoursesDao) = SemesterCourse(
    semesterID = dao.semesterID.value,
    semesterCourseID = dao.id.value,
    courseID = dao.courseID.value,
)