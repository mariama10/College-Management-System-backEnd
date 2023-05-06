package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Courses")
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String type;
    private String code;
    private String name;
    private String description;
    private int credits;
    private String semester;
    private String startDate;
    private String endDate;
    private boolean inactive=false;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<StudentCourses> studentCourses= new HashSet<>();
}
