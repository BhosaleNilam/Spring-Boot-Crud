package com.student.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "professor")
    private Set<Course> courses = new HashSet<>();

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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public boolean canTeachMoreSubjects() {
        return this.courses.size() < 3;
    }

    public void assignCourse(Course course) {

        if(canTeachMoreSubjects()){
            this.courses.add(course);
            course.setProfessor(this);
        }else{
            throw new IllegalArgumentException("Cannot teach more than 3 subjects.");
        }
    }
}
