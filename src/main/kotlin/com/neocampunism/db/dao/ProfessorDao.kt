package com.neocampunism.db.dao

import com.neocampunism.db.Professors
import com.neocampunism.model.Professor
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProfessorDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProfessorDao>(Professors)

    var professorId by Professors.professorID
    var firstName by Professors.firstName
    var lastName by Professors.lastName
    var shortName by Professors.shortName
    var departmentID by Professors.departmentID
    var email by Professors.email
}

fun daoToModel(dao: ProfessorDao) =  Professor(
    professorID = dao.professorId,
    firstName = dao.firstName,
    lastName = dao.lastName,
    shortName = dao.shortName,
    departmentID = dao.departmentID,
    email = dao.email,
)