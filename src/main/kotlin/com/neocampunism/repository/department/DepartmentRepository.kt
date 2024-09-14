package com.neocampunism.repository.department

import com.neocampunism.model.Department

interface DepartmentRepository {
    suspend fun addDepartment(department: Department): Boolean
    suspend fun getAllDepartments(): List<Department>
    suspend fun getDepartmentById(id: Int): Department?
    suspend fun updateDepartment(id: Int, department: Department): Boolean
    suspend fun deleteDepartment(id: Int): Boolean
}
