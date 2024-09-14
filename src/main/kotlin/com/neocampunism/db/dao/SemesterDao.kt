package com.neocampunism.db.dao

import com.neocampunism.db.Semesters
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SemesterDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SemesterDao>(Semesters)
    var semesterId by Semesters.semesterID
    var semesterName by Semesters.semesterName
    var semesterCode by Semesters.semesterCode
}