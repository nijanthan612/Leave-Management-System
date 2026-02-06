package com.example.demo.controller;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.entity.LeaveRequest;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LeaveRepository;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveRepository repo;

    @Autowired
    private EmployeeRepository empRepo;

    // ================= APPLY LEAVE =================
    @PostMapping("/apply/{empId}")
    public String applyLeave(
            @PathVariable int empId,
            @RequestBody LeaveRequest leave) {

        Employee emp = empRepo.findById(empId).orElse(null);
        if (emp == null) {
            return "Employee not found";
        }

        if (leave.getFromDate() == null) {
            return "From date is required";
        }

        if (leave.getToDate() == null) {
            leave.setToDate(leave.getFromDate());
        }

        if (leave.getReason() == null || leave.getReason().trim().isEmpty()) {
            return "Leave reason is required";
        }

        leave.setEmployee(emp);
        leave.setStatus("PENDING");
        leave.setActionBy(null);
        leave.setActionOn(null);

        repo.save(leave);
        return "Leave Applied Successfully";
    }

    // ================= VIEW ALL =================
    @GetMapping("/all")
    public List<LeaveRequest> getAllLeaves() {
        return repo.findAll();
    }

    // ================= APPROVE =================
    @PutMapping("/approve/{id}")
    public String approveLeave(
            @PathVariable int id,
            @RequestParam String adminName) {

        LeaveRequest leave = repo.findById(id).orElse(null);
        if (leave == null) return "Leave not found";

        leave.setStatus("APPROVED");
        leave.setActionBy(adminName);
        leave.setActionOn(LocalDateTime.now());

        repo.save(leave);
        return "Leave Approved";
    }

    // ================= REJECT =================
    @PutMapping("/reject/{id}")
    public String rejectLeave(
            @PathVariable int id,
            @RequestParam String adminName) {

        LeaveRequest leave = repo.findById(id).orElse(null);
        if (leave == null) return "Leave not found";

        leave.setStatus("REJECTED");
        leave.setActionBy(adminName);
        leave.setActionOn(LocalDateTime.now());

        repo.save(leave);
        return "Leave Rejected";
    }

    // ================= EMPLOYEE HISTORY =================
    @GetMapping("/employee/{empId}")
    public List<LeaveRequest> getLeavesByEmployee(@PathVariable int empId) {
        return repo.findByEmployee_Id(empId);
    }
}
