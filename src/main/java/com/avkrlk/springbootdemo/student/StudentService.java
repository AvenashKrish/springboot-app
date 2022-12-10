package com.avkrlk.springbootdemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student newStudent) {
        Optional<Student> studentByEmail = studentRepository
                .findStudentByEmail(newStudent.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email already exists");
        }
        studentRepository.save(newStudent);
    }

    public void deleteStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            studentRepository.deleteById(studentId);
            return;
        }
        throw new IllegalStateException("Student does not exist");
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        if (studentRepository.existsById(studentId)) {
            Optional<Student> student = studentRepository.findById(studentId);
            student.get().setName(name);
            student.get().setEmail(email);
            //studentRepository.save(student.get());
            return;
        }
        throw new IllegalStateException("Student does not exist");
    }
}
