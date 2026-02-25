package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    
    // Task 5.4: Custom query methods
    List<Student> findByDepartment(String department);
    List<Student> findByAge(int age);
}