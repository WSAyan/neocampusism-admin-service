package com.neocampunism.repository.course_professor

import com.neocampunism.model.CourseProfessor
import com.neocampunism.model.Professor

interface CourseProfessorRepository {
    suspend fun addCourseProfessor(courseProfessor: CourseProfessor): CourseProfessor?
    suspend fun getAllCourseProfessors(): List<CourseProfessor>
    suspend fun getCourseProfessorById(id: Int): CourseProfessor?
    suspend fun updateCourseProfessor(id: Int, courseProfessor: CourseProfessor): CourseProfessor?
    suspend fun deleteCourseProfessor(id: Int): Boolean
}