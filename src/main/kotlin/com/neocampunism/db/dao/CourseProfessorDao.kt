package com.neocampunism.db.dao

import com.neocampunism.db.CourseProfessors
import com.neocampunism.model.CourseProfessor
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CourseProfessorDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CourseProfessorDao>(CourseProfessors)

    var professorId by CourseProfessors.professorID
    var courseID by CourseProfessors.courseID
}

fun daoToModel(courseProfessorDao: CourseProfessorDao) = CourseProfessor(
    courseProfessorID = courseProfessorDao.id.value,
    course = CourseDao.findById(courseProfessorDao.courseID)?.let { daoToModel(it) },
    professor = ProfessorDao.findById(courseProfessorDao.professorId)?.let { daoToModel(it) },
)