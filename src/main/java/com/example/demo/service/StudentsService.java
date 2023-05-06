package com.example.demo.service;

import com.example.demo.entity.Students;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.CoursesRepository;
import com.example.demo.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private CoursesRepository coursesRepository;


    public List<Students> getAllStudents(boolean inactive) throws Exception{
        List<Students> students = studentsRepository.findAll();
        if (students.isEmpty()){
            throw new UserNotFoundException("Students not found");
        }
        return students;
    }
    public Students getStudentById(int id) throws Exception {
        return studentsRepository.findById(id).orElseThrow(()->
                new UserNotFoundException("User by id "+id+" not found"));
    }
    public List<Students> getStudentsByStatus(boolean inactive) {
        List<Students> students=studentsRepository.findAll();
        students = students.stream().filter(student -> student.isInactive() == inactive).collect(Collectors.toList());

        return students;
    }
    public  List<Students> getStudentByIdOrName(int sid, String fname, String lname) throws Exception {
        List<Students> list2;
        String[] name=new String[2];
        if (fname.contains(" ")){
            name=fname.split(" ");
            list2=studentsRepository.findByFnameContainingOrLnameContaining(name[0], name[1]);
        }
        else {
            list2=studentsRepository.findByFnameContainingOrLnameContaining(fname, lname);
        }
            Students student=studentsRepository.findById(sid).orElse(null);
            if (student!=null) {
                list2.add(student);
            }

        if (list2.isEmpty()){
            list2=new ArrayList<>();
            throw new Exception("Student not found");
        }
        return list2;

    }
    public Students addStudent(Students student) throws Exception {
        Students tempStudent = studentsRepository.findBySsn(student.getSsn());
        if(tempStudent != null){
            throw new Exception("Student already exists");
        }
        studentsRepository.save(student);
        student.setPassword(String.valueOf(student.getSid()));
        student.setEmail(createEmail(student).toLowerCase()+"@gmail.com");
        return studentsRepository.save(student);
    }

        public String createEmail(Students student){
        String fName = student.getFname();
        String lName = student.getLname();
        String username = lName+fName.substring(0,2);
        List<Students> usersExist = studentsRepository.findByEmailContaining(username);

        if (usersExist.size() == 0) {
            return username;
        } else if (usersExist.size() < 10) {
             username = username.concat(String.valueOf(usersExist.size()));
             return username;
        } else
             username = username.substring(0,username.length()-1);

            return username;
    }

    public void deleteStudent(int studentId) throws Exception {
        Students student= studentsRepository.findById(studentId).get();
        student.setInactive(true);
        updateStudent(student);

    }
        public Students updateStudent(Students student) throws Exception {
                return studentsRepository.save(student);
        }
}

