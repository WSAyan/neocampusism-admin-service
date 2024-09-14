package com.neocampunism.service.department

import com.neocampunism.model.Department
import com.neocampunism.repository.department.DepartmentRepository
import com.neocampunism.response.ApiResponse

class DepartmentServiceImpl(private val departmentRepository: DepartmentRepository) : DepartmentService {

    override suspend fun createDepartment(department: Department): ApiResponse<Department> {
        val newDepartment = departmentRepository.addDepartment(department)
            ?: return ApiResponse(
                status = "failed",
                message = "Failed to create department"
            )

        return ApiResponse(
            status = "success",
            message = "Department created",
            data = newDepartment,
        )
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
        val department = departmentRepository.getDepartmentById(id) ?: return ApiResponse(
            status = "failed",
            message = "Department not found"
        )

        return ApiResponse(status = "success", message = "Department retrieved", data = department)
    }

    override suspend fun updateDepartment(id: Int, department: Department): ApiResponse<Department> {
        val updatedDepartment = departmentRepository.updateDepartment(id, department)
            ?: return ApiResponse(
                status = "failed",
                message = "Failed to update department"
            )

        return ApiResponse(status = "success", message = "Department updated", data = updatedDepartment)
    }

    override suspend fun deleteDepartment(id: Int): ApiResponse<Any> {
        return if (departmentRepository.deleteDepartment(id)) {
            ApiResponse(status = "success", message = "Department deleted")
        } else {
            ApiResponse(status = "failed", message = "Failed to delete department")
        }
    }
}
