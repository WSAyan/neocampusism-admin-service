package com.neocampunism.db.dao

import com.neocampunism.db.Departments
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class DepartmentDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DepartmentDao>(Departments)

    var departmentId by Departments.departmentID
    var departmentName by Departments.departmentName
    var departmentCode by Departments.departmentCode
}