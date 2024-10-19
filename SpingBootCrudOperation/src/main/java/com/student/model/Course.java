package com.student.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name")
    private String name;

    @ManyToOne
    @JoinColumn(name ="professor_id")
    private Professor professor;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> employees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Set<Student> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Student> employees) {
        this.employees = employees;
    }
}
