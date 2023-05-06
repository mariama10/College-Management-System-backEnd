package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="Student_Courses")
public class StudentCourses {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private int id;
    @ManyToOne
    @JoinColumn(name = "sid")
    private Students student;
    @ManyToOne
    @JoinColumn(name = "cid")
    private Courses course;
    private int year;
    private String grade;
    private String semester;
    private String status; //completed or in-progress or withdraw or failed



    public StudentCourses(Students student, Courses course, int year, String grade, String semester, String status){
        this.student = student;
        this.course = course;
        this.year = year;
        this.grade = grade;
        this.semester = semester;
        this.status = status;
    }
}