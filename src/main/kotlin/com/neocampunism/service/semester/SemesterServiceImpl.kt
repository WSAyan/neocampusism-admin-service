package com.neocampunism.service.semester

import com.neocampunism.model.Semester
import com.neocampunism.repository.semester.SemesterRepositoryImpl
import com.neocampunism.response.ApiResponse

class SemesterServiceImpl(private val semesterRepository: SemesterRepositoryImpl) : SemesterService {
    override suspend fun createSemester(semester: Semester): ApiResponse<Semester> {
        return ApiResponse(
            status = "success",
            message = "Semester created",
            data = semesterRepository.addSemester(semester)
                ?: return ApiResponse(
                    status = "failed",
                    message = "Failed to create semester"
                )
        )
    }

    override suspend fun getSemesters(): ApiResponse<Map<String, List<Semester>>> {
        return ApiResponse(
            status = "success",
            message = "Semesters retrieved",
            data = mapOf("semesters" to semesterRepository.getAllSemesters())
        )
    }

    override suspend fun getSemester(id: Int): ApiResponse<Semester> {
        return ApiResponse(
            status = "success",
            message = "Semester retrieved",
            data = semesterRepository.getSemesterById(id)
                ?: return ApiResponse(status = "failed", message = "Semester not found")
        )
    }

    override suspend fun updateSemester(id: Int, semester: Semester): ApiResponse<Semester> {
        return ApiResponse(
            status = "success",
            message = "Semester updated",
            data = semesterRepository.updateSemester(id, semester)
                ?: return ApiResponse(
                    status = "failed",
                    message = "Failed to update semester"
                )
        )
    }

    override suspend fun deleteSemester(id: Int): ApiResponse<Semester> {
        return if (semesterRepository.deleteSemester(id)) {
            ApiResponse(status = "success", message = "Semester deleted")
        } else {
            ApiResponse(status = "failed", message = "Failed to delete semester")
        }
    }
}