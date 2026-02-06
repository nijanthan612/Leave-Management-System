package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reason;
    private String status; // PENDING / APPROVED / REJECTED

    private LocalDate fromDate;
    private LocalDate toDate;

    // 🔥 NEW FIELDS
    private String actionBy;          // Manager / HR name
    private LocalDateTime actionOn;   // Approved / Rejected time

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // ===== GETTERS & SETTERS =====

    public int getId() { return id; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }

    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }

    public String getActionBy() { return actionBy; }
    public void setActionBy(String actionBy) { this.actionBy = actionBy; }

    public LocalDateTime getActionOn() { return actionOn; }
    public void setActionOn(LocalDateTime actionOn) { this.actionOn = actionOn; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}
