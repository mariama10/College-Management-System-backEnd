package com.example.demo.service;

import com.example.demo.entity.Courses;
import com.example.demo.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CoursesService {

    @Autowired
    private CoursesRepository coursesRepository;

    public List<Courses> getAllCourses() throws Exception {
        List<Courses> list=coursesRepository.findAll();
        if (list.isEmpty()){
            throw new Exception("No Courses Exist!");
        }
        return list;
    }
    public Optional<Courses> getCourseById(int id) throws Exception {
        Optional<Courses> courses=coursesRepository.findById(id);
        if (courses.isEmpty()){
            Exception(String.valueOf(id), "");
        }
        return courses;
    }
    public List<Courses> getCoursesByStatus(boolean inactive) {
        List<Courses> courses=coursesRepository.findAll();
        courses = courses.stream().filter(course -> course.isInactive() == inactive).collect(Collectors.toList());

        return courses;
    }
    public List<Courses> getCourseByCodeOrName(String value) {
        List<Courses> courses=coursesRepository.findByNameContaining(value);
        List<Courses> courses2=coursesRepository.findByTypeContainingOrCodeContaining(value, value);
        if (courses2!=null){
            for (int i=0; i<courses2.size(); i++){
                if(!courses.contains(courses2.get(i))) {
                    courses.add(courses2.get(i));
                }
            }
        }
        return courses;
    }
    public Courses addNewCourse(Courses course) throws Exception {
        Courses courses=coursesRepository.
                findByTypeAndCode((course.getType()),(course.getCode()));
        if (courses!=null){
            throw new Exception(course.getType()+" "+course.getCode()+" already exists");
        }
        else{
            coursesRepository.save(course);
            course.setType(course.getType().toUpperCase());
        }
           return coursesRepository.save(course);

    }

    public Courses updateCourse(Courses course) throws Exception {
            return coursesRepository.save(course);

    }
    public void deleteCourse(int courseID) throws Exception {
        Courses course= coursesRepository.findById(courseID).get();
        course.setInactive(true);
        course.setStartDate("N/A");
        course.setEndDate("N/A");
        course.setSemester("N/A");
        updateCourse(course);
    }
    public void Exception(String data1, String data2) throws Exception {
        if (data2!=""){
            throw new Exception (data1 +" "+data2+ " does not exist" );
        }
        else{
            throw new Exception(data1+ " does not exist");
        }
    }

}


