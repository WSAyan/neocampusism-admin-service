package com.neocampunism.repository.department

import com.neocampunism.model.Department

class FakeDepartmentRepository : DepartmentRepository {
    private val departments =
        mutableListOf<Department>(
            Department(
                departmentID = 1,
                departmentName = "cse",
                departmentCode = "CSE-100"),
            Department(
                departmentID = 2,
                departmentName = "eee",
                departmentCode = "EEE-101"),
            Department(
                departmentID = 3,
                departmentName = "civil",
                departmentCode = "CIVIL-102"),
        )
    private var nextId = 1

    override suspend fun addDepartment(department: Department): Department? {
        val newDepartment = department.copy(departmentID = nextId++)
        departments.add(department.copy(departmentID = nextId++))
        return newDepartment
    }

    override suspend fun getAllDepartments(): List<Department> {
        return departments.toList()
    }

    override suspend fun getDepartmentById(id: Int): Department? {
        return departments.find { it.departmentID == id }
    }

    override suspend fun updateDepartment(id: Int, department: Department): Boolean {
        val index = departments.indexOfFirst { it.departmentID == id }
        if (index != -1) {
            departments[index] = department.copy(departmentID = id)
            return true
        }
        return false
    }

    override suspend fun deleteDepartment(id: Int): Boolean {
        return departments.removeIf { it.departmentID == id }
    }
}