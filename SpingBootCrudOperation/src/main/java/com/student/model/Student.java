package com.student.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))

    private Set<Course> courses = new HashSet<>();


    public boolean canEnrollForCourse(){
        return this.courses.size() <5;
    }

    public void enrollCourse(Course course){

        if(canEnrollForCourse()){
            this.courses.add(course);
        }else {
            throw new IllegalArgumentException("Cant enroll in more than 5 subjects");
        }

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
