package com.neocampunism.db.dao

import com.neocampunism.db.Semesters
import com.neocampunism.model.Semester
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SemesterDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SemesterDao>(Semesters)

    var semesterName by Semesters.semesterName
    var semesterCode by Semesters.semesterCode
}

fun daoToModel(dao: SemesterDao) = Semester(
    semesterID = dao.id.value,
    semesterName = dao.semesterName,
    semesterCode = dao.semesterCode,
)