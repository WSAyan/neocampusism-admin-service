package com.neocampunism.service.semester_course

import com.neocampunism.model.SemesterCourse
import com.neocampunism.model.TimeSlot
import com.neocampunism.repository.semester_course.SemestersCoursesRepository
import com.neocampunism.response.ApiResponse

class SemesterCourseServiceImpl(private val semesterCourseRepository: SemestersCoursesRepository) : SemesterCourseService {
    override suspend fun createSemesterCourse(semesterCourse: SemesterCourse): ApiResponse<SemesterCourse> {
        return if (semesterCourseRepository.addSemesterCourse(semesterCourse) != null) {
            ApiResponse(status = "success", message = "Semester Course mapping created", data = semesterCourse)
        } else {
            ApiResponse(status = "failed", message = "Failed to create Semester Course mapping")
        }
    }

    override suspend fun getAllSemesterCourses(): ApiResponse<Map<String, List<SemesterCourse>>> {
        val timeSlots = semesterCourseRepository.getAllSemesterCourses()
        return ApiResponse(
            status = "success",
            message = "Semester Course mapping retrieved",
            data = mapOf("semestersCourses" to timeSlots)
        )
    }

    override suspend fun getAllSemesterCourses(id: Int): ApiResponse<SemesterCourse> {
        val timeSlot = semesterCourseRepository.getSemesterCourseById(id)
        return if (timeSlot != null) {
            ApiResponse(status = "success", message = "Semester Course mapping retrieved", data = timeSlot)
        } else {
            ApiResponse(status = "failed", message = "Semester Course mapping not found")
        }
    }

    override suspend fun updateSemesterCourse(id: Int, semesterCourse: SemesterCourse): ApiResponse<SemesterCourse> {
        return if (semesterCourseRepository.updateSemesterCourse(id, semesterCourse) != null) {
            ApiResponse(status = "success", message = "Semester Course mapping updated", data = semesterCourse)
        } else {
            ApiResponse(status = "failed", message = "Failed to update Semester Course mapping")
        }
    }

    override suspend fun deleteSemesterCourse(id: Int): ApiResponse<SemesterCourse> {
        return if (semesterCourseRepository.deleteSemesterCourse(id)) {
            ApiResponse(status = "success", message = "Semester Course mapping deleted")
        } else {
            ApiResponse(status = "failed", message = "Failed to delete Semester Course mapping")
        }
    }
}
