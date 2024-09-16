package com.neocampunism.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.time


object Departments : IntIdTable("departments") {
    val departmentName = varchar("department_name", 100)
    val departmentCode = varchar("department_code", 100)
}


object Professors : IntIdTable("professors") {
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    val shortName = varchar("short_name", 10)
    val departmentID = reference("department_id", Departments.id).nullable()
    val email = varchar("email", 100).nullable()
    val phone = varchar("phone", 100).nullable()
}


object Semesters : IntIdTable("semesters") {
    val semesterName = varchar("semester_name", 50)
    val semesterCode = varchar("semester_code", 5)
}


object Courses : IntIdTable("courses") {
    val courseName = varchar("course_name", 100)
    val courseCode = varchar("course_code", 20)
    val departmentID = reference("department_id", Departments.id).nullable()
    val credits = integer("credits")
    val courseType = enumerationByName("course_type", 6, CourseType::class)
}


enum class CourseType {
    Theory, Lab
}

object CourseProfessors : IntIdTable("course_professors") {
    val courseID = reference("course_id", Courses.id)
    val professorID = reference("professor_id", Professors.id)
}


object SemestersCourses : IntIdTable("semesters_courses") {
    val semesterID = reference("semester_id", Semesters.id)
    val courseID = reference("course_id", Courses.id)
}


object Rooms : IntIdTable("rooms") {
    val roomNumber = varchar("room_number", 10)
    val capacity = integer("capacity")
    val roomType = enumerationByName("room_type", 9, RoomType::class)
}


enum class RoomType {
    Classroom, Lab
}

object TimeSlots : IntIdTable("time_slots") {
    val dayOfWeek = enumerationByName("day_of_week", 7, DayOfWeek::class)
    val startTime = time("start_time")
    val endTime = time("end_time")
}


enum class DayOfWeek {
    Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
}

object Schedules : IntIdTable("schedules") {
    val courseID = reference("course_id", Courses.id)
    val timeSlotID = reference("time_slot_id", TimeSlots.id)
    val roomID = reference("room_id", Rooms.id)

    init {
        uniqueIndex(courseID, timeSlotID, roomID)
    }
}


