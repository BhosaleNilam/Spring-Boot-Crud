package com.student.controller;

import com.student.model.Professor;
import com.student.model.Student;
import com.student.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/employees/{studentId}/courses/{courseId}")
    public Student enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId){
        return enrollmentService.enrollInCourse(studentId, courseId);

    }

    @PostMapping("/professors/{professorId}/courses/{courseId}")
    public Professor assignProfessor(@PathVariable Long professorId, @PathVariable Long courseId){
        return enrollmentService.assignProfessorToCourse(professorId, courseId);
    }
}
