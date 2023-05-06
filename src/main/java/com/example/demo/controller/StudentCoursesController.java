package com.example.demo.controller;

import com.example.demo.entity.StudentCourses;
import com.example.demo.service.StudentCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/studentCourses")
@RestController
public class StudentCoursesController {

    @Autowired
    private StudentCoursesService student_coursesService;

    //list all courses that completed, withdrew, failed, in-progress
    @GetMapping("/getStudentCourses/{sid}")
    public ResponseEntity<List<StudentCourses>> getStudentCourses(@PathVariable int sid, @RequestParam(value = "status", required = false) String status) throws Exception {
        return new ResponseEntity<List<StudentCourses>>(student_coursesService.getStudentCourses(sid, status), HttpStatus.OK);
    }

    //# of credits completed, in progress, etc
    @GetMapping("/getCredits/{sid}")
    public ResponseEntity<Integer> getCredits (@PathVariable int sid, @RequestParam String status) throws Exception {
        return new ResponseEntity<Integer>(student_coursesService.getCredits(sid, status), HttpStatus.OK);
    }
    @PutMapping("{sid}/addCourseToStudent/{cid}")
    public ResponseEntity<StudentCourses> addCourseToStudent(@PathVariable int sid, @PathVariable int cid, @RequestParam int year,
                                                @RequestParam String grade,@RequestParam String semester, @RequestParam String status) throws Exception {
        student_coursesService.addCourseToStudent(sid, cid, year, grade, semester, status);
        return new ResponseEntity<StudentCourses>(HttpStatus.OK);
    }

}
