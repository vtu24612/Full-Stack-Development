package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController // Changed from @Controller for Postman 
@RequestMapping("/api/students") // Base URL for all endpoints
public class StudentController {

    @Autowired
    private StudentRepository repo;

    // 1. CREATE (Postman POST)
    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student) {
        return repo.save(student); // Returns the saved JSON data
    }

    // 2. READ ALL (Postman GET)
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // 3. READ BY ID (Postman GET)
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    // 4. UPDATE (Postman PUT)
    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        Student existingStudent = repo.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setDepartment(updatedStudent.getDepartment());
            existingStudent.setAge(updatedStudent.getAge());
            return repo.save(existingStudent);
        }
        return null;
    }

    // 5. DELETE (Postman DELETE)
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        repo.deleteById(id);
        return "Student with ID " + id + " deleted successfully!";
    }
    
 // 6. READ BY DEPARTMENT (Task 5.4)
    @GetMapping("/department/{department}")
    public List<Student> getStudentsByDepartment(@PathVariable String department) {
        return repo.findByDepartment(department);
    }

    // 7. READ BY AGE (Task 5.4)
    @GetMapping("/age/{age}")
    public List<Student> getStudentsByAge(@PathVariable int age) {
        return repo.findByAge(age);
    }
    
 // 8. PAGINATION AND SORTING (Task 5.5)
    @GetMapping("/paginated")
    public Page<Student> getPaginatedAndSortedStudents(
            @RequestParam(defaultValue = "0") int page,      // Page number (starts at 0)
            @RequestParam(defaultValue = "5") int size,      // How many records per page
            @RequestParam(defaultValue = "id") String sortBy, // Field to sort by
            @RequestParam(defaultValue = "ASC") String sortDir) { // ASC or DESC

        // 1. Decide if we are sorting Ascending (A-Z, 1-9) or Descending (Z-A, 9-1)
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? 
                    Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // 2. Create the "Pageable" object with the page number, size, and sorting rules
        Pageable pageable = PageRequest.of(page, size, sort);

        // 3. Let the repository do the heavy lifting
        return repo.findAll(pageable);
    }
}