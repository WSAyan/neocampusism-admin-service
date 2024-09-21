package com.neocampunism.db.sql.dao

import com.neocampunism.db.sql.Courses
import com.neocampunism.model.Course
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CourseDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CourseDao>(Courses)

    var courseName by Courses.courseName
    var courseCode by Courses.courseCode
    var departmentID by Courses.departmentID
    var credits by Courses.credits
    var courseType by Courses.courseType
}

fun daoToModel(dao: CourseDao) = Course(
    courseID = dao.id.value,
    courseName = dao.courseName,
    courseCode = dao.courseCode,
    departmentID = dao.departmentID?.value,
    credits = dao.credits,
    courseType = com.neocampunism.model.CourseType.valueOf(dao.courseType.name)
)