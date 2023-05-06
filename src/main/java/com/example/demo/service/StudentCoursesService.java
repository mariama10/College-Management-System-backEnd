package com.example.demo.service;

import com.example.demo.entity.Courses;
import com.example.demo.entity.StudentCourses;
import com.example.demo.entity.Students;
import com.example.demo.repository.CoursesRepository;
import com.example.demo.repository.StudentCoursesRepository;
import com.example.demo.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCoursesService {

    @Autowired
    private StudentCoursesRepository studentCoursesRepository;

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    public List<StudentCourses> getStudentCourses(int sid, String status) throws Exception {

        List<StudentCourses> studentCourses;
        if(status.equals("all")){
            studentCourses = studentCoursesRepository.findByStudent(studentsRepository.findById(sid).get());
        }
        else if (status.equals("attempted")){
            studentCourses = studentCoursesRepository.findByStudentAndStatus(studentsRepository.findById(sid).get(), "failed");
            List<StudentCourses> studentCourses1=studentCoursesRepository.findByStudentAndStatus(studentsRepository.findById(sid).get(), "withdrew");
            for (int i=0; i<studentCourses1.size(); i++){
                studentCourses.add(studentCourses1.get(i));
            }
        }
        else{
            studentCourses = studentCoursesRepository.findByStudentAndStatus(studentsRepository.findById(sid).get(), status);
        }
        if (studentCourses==null){
            throw new Exception("No Courses Found!");
        }
        return studentCourses;
    }

    public int getCredits(int sid, String status) throws Exception {
        List<StudentCourses> studentCourses= getStudentCourses(sid, status);
        int credits = 0;
        credits = studentCourses.stream().mapToInt(studentCourse -> studentCourse.getCourse().getCredits()).sum();
        return credits;
    }


    public StudentCourses addCourseToStudent(int sid, int cid, int year, String grade, String semester, String status) throws Exception {
        Students student = studentsRepository.findById(sid).get();
        if (student.isInactive() == true){
            throw new Exception("Student is inactive");
        }
        Courses courses = coursesRepository.findById(cid).get();
        StudentCourses studentCoursesExist= studentCoursesRepository.findByStudentAndCourse(student, courses);
        if (studentCoursesExist!=null && studentCoursesExist.getStatus().equals("Completed")){
            throw new Exception("Course already completed");
        }
        StudentCourses studentCourses = new StudentCourses(student, courses, year, grade, semester, status);
        return studentCoursesRepository.save(studentCourses);
    }


}
