package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository empRepo;

    // Register new employee
    @PostMapping("/register")
    public Employee registerEmployee(@RequestBody Employee emp) {
        return empRepo.save(emp);
    }
    @PostMapping("/login")
    public Employee login(@RequestBody Employee emp) {
        return empRepo.findByNameAndPassword(emp.getName(), emp.getPassword());
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return empRepo.findById(id).orElse(null);
    }


}
