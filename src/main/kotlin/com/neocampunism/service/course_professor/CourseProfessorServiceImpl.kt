package com.neocampunism.service.course_professor

import com.neocampunism.model.CourseProfessor
import com.neocampunism.repository.course_professor.CourseProfessorRepository
import com.neocampunism.response.ApiResponse

class CourseProfessorServiceImpl(val courseProfessorRepository: CourseProfessorRepository) : CourseProfessorService {
    override suspend fun createCourseProfessor(courseProfessor: CourseProfessor): ApiResponse<CourseProfessor> {
        return ApiResponse(
            status = "success",
            message = "Course professor created",
            data = courseProfessorRepository.addCourseProfessor(courseProfessor)
                ?: return ApiResponse(
                    status = "failed",
                    message = "Failed to create course"
                )
        )
    }

    override suspend fun getCourseProfessors(): ApiResponse<Map<String, List<CourseProfessor>>> {
        return ApiResponse(
            status = "success",
            message = "Course Professors retrieved",
            data = mapOf("rooms" to courseProfessorRepository.getAllCourseProfessors())
        )
    }

    override suspend fun getCourseProfessor(id: Int): ApiResponse<CourseProfessor> {
        return ApiResponse(
            status = "success",
            message = "Course Professors retrieved",
            data = courseProfessorRepository.getCourseProfessorById(id)
                ?: return ApiResponse(status = "failed", message = "Course Professors not found")
        )
    }

    override suspend fun updateCourseProfessor(
        id: Int,
        courseProfessor: CourseProfessor
    ): ApiResponse<CourseProfessor> {
        return ApiResponse(
            status = "success",
            message = "Course Professor updated",
            data = courseProfessorRepository.updateCourseProfessor(id, courseProfessor)
                ?: return ApiResponse(
                    status = "failed",
                    message = "Failed to update Course Professor"
                )
        )
    }

    override suspend fun deleteCourseProfessor(id: Int): ApiResponse<CourseProfessor> {
        return if (courseProfessorRepository.deleteCourseProfessor(id)) {
            ApiResponse(status = "success", message = "Course Professor deleted")
        } else {
            ApiResponse(status = "failed", message = "Failed to delete Course Professor")
        }
    }
}