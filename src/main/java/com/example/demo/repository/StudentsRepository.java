package com.example.demo.repository;

import com.example.demo.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Students, Integer> {
    List<Students> findByEmailContaining(String email);
    List<Students> findByFnameContainingOrLnameContaining(String fName, String lName);
    Students findBySsn(String ssn);
}
