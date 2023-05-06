package com.example.demo.configuration;

import com.example.demo.entity.Students;
import com.example.demo.repository.StudentsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Configuration
public class StudentConfig {
   // @Bean
   /* CommandLineRunner commandLineRunner(StudentsRepository repository){
      return args -> {
         Students Aima= new Students(
                  1117219,
                  "Aim",
                  "Ash",
                  'F',
                  "address",
                  "a@gmail.com",
                  "chem",
                  "07008",
                  new Date(2020, 01, 01),
                  new HashSet<>()
          );
          repository.saveAll(
                  List.of(Aima)
          );
      }  ;
    }*/
}
