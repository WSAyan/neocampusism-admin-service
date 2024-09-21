package com.neocampunism.db.sql.dao

import com.neocampunism.db.sql.CourseProfessors
import com.neocampunism.db.sql.Courses
import com.neocampunism.db.sql.Professors
import com.neocampunism.model.Professor
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProfessorDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProfessorDao>(Professors)

    var firstName by Professors.firstName
    var lastName by Professors.lastName
    var shortName by Professors.shortName
    var departmentID by Professors.departmentID
    var email by Professors.email
    var phone by Professors.phone
}

fun daoToModel(dao: ProfessorDao) =  Professor(
    professorID = dao.id.value,
    firstName = dao.firstName,
    lastName = dao.lastName,
    shortName = dao.shortName,
    departmentID = dao.departmentID?.value,
    email = dao.email,
    phone = dao.phone,
    courses = (CourseProfessors innerJoin Courses)
        .select(Courses.columns)
        .where {
            CourseProfessors.professorID eq dao.id
        }
        .map { row ->
            daoToModel(CourseDao.wrapRow(row))
        }
)