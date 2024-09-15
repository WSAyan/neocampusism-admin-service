package com.neocampunism.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.time


object Departments : IntIdTable("Departments") {
    val departmentName = varchar("DepartmentName", 100)
    val departmentCode = varchar("DepartmentCode", 100)
}


object Professors : IntIdTable("Professors") {
    val firstName = varchar("FirstName", 50)
    val lastName = varchar("LastName", 50)
    val shortName = varchar("ShortName", 10)
    val departmentID = reference("DepartmentID", Departments.id).nullable()
    val email = varchar("Email", 100).nullable()
    val phone = varchar("phone", 100).nullable()
}


object Semesters : IntIdTable("Semesters") {
    val semesterName = varchar("SemesterName", 50)
    val semesterCode = varchar("SemesterCode", 5)
}


object Courses : IntIdTable("Courses") {
    val courseName = varchar("CourseName", 100)
    val courseCode = varchar("CourseCode", 20)
    val departmentID = reference("DepartmentID", Departments.id).nullable()
    val credits = integer("Credits")
    val courseType = enumerationByName("CourseType", 6, CourseType::class)
}


enum class CourseType {
    Theory, Lab
}

object CourseProfessors : IntIdTable("CourseProfessors") {
    val courseID = reference("CourseID", Courses.id)
    val professorID = reference("ProfessorID", Professors.id)
}


object SemestersCourses : IntIdTable("SemestersCourses") {
    val semesterID = reference("SemesterID", Semesters.id)
    val courseID = reference("CourseID", Courses.id)
}


object Rooms : IntIdTable("Rooms") {
    val roomNumber = varchar("RoomNumber", 10)
    val capacity = integer("Capacity")
    val roomType = enumerationByName("RoomType", 9, RoomType::class)
}


enum class RoomType {
    Classroom, Lab
}

object TimeSlots : IntIdTable("TimeSlots") {
    val dayOfWeek = enumerationByName("DayOfWeek", 7, DayOfWeek::class)
    val startTime = time("StartTime")
    val endTime = time("EndTime")
}


enum class DayOfWeek {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
}

object Schedules : IntIdTable("Schedules") {
    val courseID = reference("CourseID", Courses.id)
    val timeSlotID = reference("TimeSlotID", TimeSlots.id)
    val roomID = reference("RoomID", Rooms.id)

    init {
        uniqueIndex(courseID, timeSlotID, roomID)
    }
}


