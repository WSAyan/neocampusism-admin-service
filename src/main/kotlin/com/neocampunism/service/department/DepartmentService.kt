package com.neocampunism.service.department

import com.neocampunism.model.Department
import com.neocampunism.response.ApiResponse

interface DepartmentService {
    suspend fun createDepartment(department: Department): ApiResponse<Department>
    suspend fun getDepartments(): ApiResponse<Map<String, List<Department>>>
    suspend fun getDepartment(id: Int): ApiResponse<Department>
    suspend fun updateDepartment(id: Int, department: Department): ApiResponse<Department>
    suspend fun deleteDepartment(id: Int): ApiResponse<Any>
}

