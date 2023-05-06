package com.example.demo.controller;

import com.example.demo.entity.Students;
import com.example.demo.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/students")
@RestController
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Students>> getAllStudents(@RequestParam(value = "inactive", required = false, defaultValue = "false") boolean inactive) throws Exception {
        return new ResponseEntity<List<Students>>(studentsService.getAllStudents(inactive), HttpStatus.OK);
    }
    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<Students> getStudentById(@PathVariable int id) throws Exception {
        return new ResponseEntity<Students>(studentsService.getStudentById(id), HttpStatus.OK);
    }
    @GetMapping("/getStudentsByStatus")
    public ResponseEntity<List<Students>> getStudentsByStatus(@RequestParam boolean inactive) {
        return new ResponseEntity<List<Students>>(studentsService.getStudentsByStatus(inactive), HttpStatus.OK);
    }
    @GetMapping("/getStudentByIdOrName")
    public ResponseEntity<List<Students>> getStudentByIdOrName(@RequestParam int sid, @RequestParam String fname, @RequestParam String lname) throws Exception {
        return new ResponseEntity<List<Students>>(studentsService.getStudentByIdOrName(sid, fname, lname), HttpStatus.OK);
    }
    //Add students
    @PostMapping("/addStudent")
    public ResponseEntity<Students> addStudent(@RequestBody Students student) throws Exception {
        return new ResponseEntity<Students>(studentsService.addStudent(student), HttpStatus.CREATED);
    }
    //Delete students
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int studentId) throws Exception {
       // return new ResponseEntity<String>(studentsService.deleteStudent(studentId), HttpStatus.OK);
        studentsService.deleteStudent(studentId);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    //Update students
    @PutMapping("/updateStudent")
    public ResponseEntity<Students> updateStudent(@RequestBody Students student) throws Exception {
        return new ResponseEntity<Students>(studentsService.updateStudent(student), HttpStatus.OK);

    }




}
