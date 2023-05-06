package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Students")
public class Students implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int sid;
    private String fname;
    private String lname;
    private String phoneNumber;
    private String dob;
    private String ssn;
    private Character gender;
    private String address;
    private String state;
    private String country;
    private String major;
    private String status; //Undergraduate  or graduate
    private String degree; //Bachelors
    @Column(unique = true)
    private String email;
    private String password;
    private final String createdDate = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
    private boolean inactive=false;


    @JsonIgnore
    @OneToMany (mappedBy = "student", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<StudentCourses> studentCourses= new HashSet<>();

}






