package com.neocampunism.repository.course

import com.neocampunism.db.CourseType
import com.neocampunism.db.Courses
import com.neocampunism.db.Departments
import com.neocampunism.db.dao.CourseDao
import com.neocampunism.db.dao.daoToModel
import com.neocampunism.db.suspendTransaction
import com.neocampunism.model.Course
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class CourseRepositoryImpl : CourseRepository {
    override suspend fun addCourse(course: Course): Course? = suspendTransaction {
        course.courseCode ?: return@suspendTransaction null

        course.courseName ?: return@suspendTransaction null

        course.courseType ?: return@suspendTransaction null

        course.credits ?: return@suspendTransaction null

        course.departmentID ?: return@suspendTransaction null

        val newCourse = CourseDao.new {
            courseName = course.courseName
            courseType = CourseType.valueOf(course.courseType.name)
            courseCode = course.courseCode
            credits = course.credits
            departmentID = EntityID(course.departmentID, Departments)
        }

        daoToModel(newCourse)
    }

    override suspend fun getAllCourses(): List<Course> = suspendTransaction {
        CourseDao.all().map(::daoToModel)
    }


    override suspend fun getCourseById(id: Int): Course? = suspendTransaction {
        CourseDao
            .find {
                Courses.id eq id
            }
            .map(::daoToModel)
            .firstOrNull()
    }


    override suspend fun updateCourse(id: Int, course: Course): Course? = suspendTransaction {
        course.courseCode ?: return@suspendTransaction null

        course.courseName ?: return@suspendTransaction null

        course.courseType ?: return@suspendTransaction null

        course.credits ?: return@suspendTransaction null

        course.departmentID ?: return@suspendTransaction null

        val updatedCourse = CourseDao.findSingleByAndUpdate(Departments.id eq id) {
            it.courseName = course.courseName
            it.courseType = CourseType.valueOf(course.courseType.name)
            it.courseCode = course.courseCode
            it.credits = course.credits
            it.departmentID = EntityID(course.departmentID, Departments)
        } ?: return@suspendTransaction null

        daoToModel(updatedCourse)
    }

    override suspend fun deleteCourse(id: Int): Boolean = suspendTransaction {
        Courses.deleteWhere { Courses.id eq id } == 1
    }
}