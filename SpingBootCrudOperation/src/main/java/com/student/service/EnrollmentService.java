package com.student.service;

import com.student.model.Course;
import com.student.model.Professor;
import com.student.model.Student;
import com.student.repository.CourseRepository;
import com.student.repository.ProfessorRepository;
import com.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EnrollmentService {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ProfessorRepository professorRepository;


    public Student enrollInCourse(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Employee Id"));


        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Course Id"));

            return studentRepository.save(student);
    }

    public Professor assignProfessorToCourse(Long professorId, Long courseId) {

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Professor Id"));


        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Course Id"));


        professor.assignCourse(course);

        return professorRepository.save(professor);



    }
}
