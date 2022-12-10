package com.avkrlk.springbootdemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner command(StudentRepository repository) {
        return args -> {
            Student alex = new Student(
                    "alex",
                    "alex@mail.com",
                    LocalDate.of(2000, 10, 10));
            Student john = new Student(
                    "john",
                    "john@mail.com",
                    LocalDate.of(2001, 10, 10));
            repository.saveAll(
                    List.of(
                            alex,
                            john
                    )
            );
        };
    }
}
