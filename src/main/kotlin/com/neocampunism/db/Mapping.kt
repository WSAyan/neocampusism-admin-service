package com.neocampunism.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.time


object Departments : IntIdTable("Departments") {
    val departmentID = integer("DepartmentID").autoIncrement().index()
    val departmentName = varchar("DepartmentName", 100)
    val departmentCode = varchar("DepartmentCode", 100)
}


object Professors : IntIdTable("Professors") {
    val professorID = integer("ProfessorID").autoIncrement().index()
    val firstName = varchar("FirstName", 50)
    val lastName = varchar("LastName", 50)
    val shortName = varchar("ShortName", 10)
    val departmentID = reference("DepartmentID", Departments.departmentID).nullable()
    val email = varchar("Email", 100).nullable()
}


object Semesters : IntIdTable("Semesters") {
    val semesterID = integer("SemesterID").autoIncrement().index()
    val semesterName = varchar("SemesterName", 50)
    val semesterCode = varchar("SemesterCode", 5)
}


object Courses : IntIdTable("Courses") {
    val courseID = integer("CourseID").autoIncrement().index()
    val courseName = varchar("CourseName", 100)
    val courseCode = varchar("CourseCode", 20)
    val departmentID = reference("DepartmentID", Departments.departmentID).nullable()
    val credits = integer("Credits")
    val courseType = enumerationByName("CourseType", 6, CourseType::class)
}


enum class CourseType {
    Theory, Lab
}

object CourseProfessors : IntIdTable("CourseProfessors") {
    val courseProfessorID = integer("CourseProfessorID").autoIncrement().index()
    val courseID = reference("CourseID", Courses.courseID)
    val professorID = reference("ProfessorID", Professors.professorID)
}


object SemestersCourses : IntIdTable("SemestersCourses") {
    val semesterCourseID = integer("SemesterCourseID").autoIncrement().index()
    val semesterID = reference("SemesterID", Semesters.semesterID)
    val courseID = reference("CourseID", Courses.courseID)
}


object Rooms : IntIdTable("Rooms") {
    val roomID = integer("RoomID").autoIncrement().index()
    val roomNumber = varchar("RoomNumber", 10)
    val capacity = integer("Capacity")
    val roomType = enumerationByName("RoomType", 9, RoomType::class)
}


enum class RoomType {
    Classroom, Lab
}

object TimeSlots : IntIdTable("TimeSlots") {
    val timeSlotID = integer("TimeSlotID").autoIncrement().index()
    val dayOfWeek = enumerationByName("DayOfWeek", 10, DayOfWeek::class)
    val startTime = time("StartTime")
    val endTime = time("EndTime")
}


enum class DayOfWeek {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
}

object Schedules : IntIdTable("Schedules") {
    val scheduleID = integer("ScheduleID").autoIncrement().index()
    val courseID = reference("CourseID", Courses.courseID)
    val timeSlotID = reference("TimeSlotID", TimeSlots.timeSlotID)
    val roomID = reference("RoomID", Rooms.roomID)

    init {
        uniqueIndex(courseID, timeSlotID, roomID)
    }
}


