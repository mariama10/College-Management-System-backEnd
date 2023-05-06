package com.example.demo.repository;


import com.example.demo.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>{
    Courses findByTypeAndCode(String type, String code);
    List<Courses> findByNameContaining(String name);
    List<Courses> findByTypeContainingOrCodeContaining(String value, String value1);
}
