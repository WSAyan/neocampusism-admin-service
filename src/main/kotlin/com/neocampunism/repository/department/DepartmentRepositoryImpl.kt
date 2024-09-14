package com.neocampunism.repository.department

import com.neocampunism.db.Departments
import com.neocampunism.db.dao.DepartmentDao
import com.neocampunism.db.dao.daoToModel
import com.neocampunism.db.suspendTransaction
import com.neocampunism.model.Department
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class DepartmentRepositoryImpl : DepartmentRepository {
    override suspend fun addDepartment(department: Department): Department? = suspendTransaction {
        department.departmentName ?: return@suspendTransaction null

        department.departmentCode ?: return@suspendTransaction null

        val newDepartment = DepartmentDao.new {
            departmentName = department.departmentName
            departmentCode = department.departmentCode
        }

        daoToModel(newDepartment)
    }

    override suspend fun getAllDepartments(): List<Department> = suspendTransaction {
        DepartmentDao.all().map(::daoToModel)
    }

    override suspend fun getDepartmentById(id: Int): Department? = suspendTransaction {
        DepartmentDao
            .find {
                Departments.id eq id
            }
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun updateDepartment(id: Int, department: Department): Department? = suspendTransaction {
        department.departmentName ?: return@suspendTransaction null

        department.departmentCode ?: return@suspendTransaction null

        val updatedDepartment = DepartmentDao.findSingleByAndUpdate(Departments.id eq id) {
            it.departmentName = department.departmentName
            it.departmentCode = department.departmentCode
        } ?: return@suspendTransaction null

        daoToModel(updatedDepartment)
    }

    override suspend fun deleteDepartment(id: Int): Boolean = suspendTransaction {
        Departments.deleteWhere { Departments.id eq id } == 1
    }
}