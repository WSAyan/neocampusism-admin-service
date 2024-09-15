package com.neocampunism.db.dao

import com.neocampunism.db.Courses
import com.neocampunism.db.SemestersCourses
import com.neocampunism.db.suspendTransaction
import com.neocampunism.model.Course
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

fun Int.coursesOfThisSemester(): List<Course> =
    (SemestersCourses innerJoin Courses)
        .select(Courses.columns)
        .where { SemestersCourses.semesterID eq this@coursesOfThisSemester }
        .map { row ->
            daoToModel(
                CourseDao.wrapRow(row)
            )
        }
