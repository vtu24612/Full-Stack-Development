package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students") // Task 5.2 requirement
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "student_name") // Task 5.2 
    private String name;

    @Column(name = "department")
    private String department; // Added for Task 5.4

    @Column(name = "age")
    private int age; // Added for Task 5.4

    // Default constructor
    public Student() {}

    // --- Getters and Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}