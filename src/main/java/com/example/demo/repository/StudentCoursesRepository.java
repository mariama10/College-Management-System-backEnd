package com.example.demo.repository;

import com.example.demo.entity.Courses;
import com.example.demo.entity.StudentCourses;
import com.example.demo.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCoursesRepository extends JpaRepository<StudentCourses, Integer> {

    List<StudentCourses> findByStudentAndStatus(Students sid, String status);
    StudentCourses findByStudentAndCourse(Students sid, Courses cid);
    List<StudentCourses> findByStudent(Students students);
}
