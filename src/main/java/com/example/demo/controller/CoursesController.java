package com.example.demo.controller;

import com.example.demo.entity.Courses;
import com.example.demo.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/courses")
@RestController
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @GetMapping("/getAllCourses")
    public ResponseEntity<List<Courses>> getAllCourses() throws Exception{
        return new ResponseEntity<List<Courses>>(coursesService.getAllCourses(), HttpStatus.OK);
    }
    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<Optional<Courses>> getCoursesById(@PathVariable int id ) throws Exception {
        return new ResponseEntity<Optional<Courses>>(coursesService.getCourseById(id), HttpStatus.OK);
    }
    @GetMapping("/getCoursesByStatus")
    public ResponseEntity<List<Courses>> getStudentsByStatus(@RequestParam boolean inactive) {
        return new ResponseEntity<List<Courses>>(coursesService.getCoursesByStatus(inactive), HttpStatus.OK);
    }
    @GetMapping("getCoursesByCodeOrName")
    public ResponseEntity<List<Courses>> getCourseByCodeOrName(@RequestParam String value) throws Exception{
        return new ResponseEntity<List<Courses>>(coursesService.getCourseByCodeOrName(value), HttpStatus.OK);
    }
    //Add Courses
    @PostMapping("/addCourse")
    public ResponseEntity<Courses> addCourses(@RequestBody Courses courses) throws Exception{
        return new ResponseEntity<Courses>(coursesService.addNewCourse(courses), HttpStatus.CREATED);
    }
    //Update students
    @PutMapping("/updateCourse")
    public ResponseEntity<Courses> updateCourse(@RequestBody Courses course) throws Exception {
        return new ResponseEntity<Courses>(coursesService.updateCourse(course), HttpStatus.OK);
    }
    //Delete Courses
    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable("id") int courseId) throws Exception {
        coursesService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
