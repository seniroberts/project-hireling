package com.botScofield.hireling.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_table")
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true)
    private String email;

    private String role;
    private Long departmentId;

}