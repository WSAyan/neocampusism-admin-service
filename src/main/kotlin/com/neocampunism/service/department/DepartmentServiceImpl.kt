package com.neocampunism.service.department

import com.neocampunism.model.Department
import com.neocampunism.repository.department.DepartmentRepository
import com.neocampunism.response.ApiResponse

class DepartmentServiceImpl(private val departmentRepository: DepartmentRepository) : DepartmentService {
    override suspend fun createDepartment(department: Department): ApiResponse<Department> {
        return if (departmentRepository.addDepartment(department)) {
            ApiResponse(status = "success", message = "Department created", data = department)
        } else {
            ApiResponse(status = "failed", message = "Failed to create department")
        }
    }

    override suspend fun getDepartments(): ApiResponse<Map<String, List<Department>>> {
        val departments = departmentRepository.getAllDepartments()
        return ApiResponse(
            status = "success",
            message = "Departments retrieved",
            data = mapOf("departments" to departments)
        )
    }

    override suspend fun getDepartment(id: Int): ApiResponse<Department> {
        val department = departmentRepository.getDepartmentById(id)
        return if (department != null) {
            ApiResponse(status = "success", message = "Department retrieved", data = department)
        } else {
            ApiResponse(status = "failed", message = "Department not found")
        }
    }

    override suspend fun updateDepartment(id: Int, department: Department): ApiResponse<Department> {
        return if (departmentRepository.updateDepartment(id, department)) {
            ApiResponse(status = "success", message = "Department updated", data = department)
        } else {
            ApiResponse(status = "failed", message = "Failed to update department")
        }
    }

    override suspend fun deleteDepartment(id: Int): ApiResponse<Any> {
        return if (departmentRepository.deleteDepartment(id)) {
            ApiResponse(status = "success", message = "Department deleted")
        } else {
            ApiResponse(status = "failed", message = "Failed to delete department")
        }
    }
}
