package com.neocampunism.repository.semester

import com.neocampunism.model.Semester
import com.neocampunism.model.TimeSlot

interface SemesterRepository {
    suspend fun addSemester(semester: Semester): Semester?
    suspend fun getAllSemesters(): List<Semester>
    suspend fun getSemesterById(id: Int): Semester?
    suspend fun updateSemester(id: Int, semester: Semester): Semester?
    suspend fun deleteSemester(id: Int): Boolean
}