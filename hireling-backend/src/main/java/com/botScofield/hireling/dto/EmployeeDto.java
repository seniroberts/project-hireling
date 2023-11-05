package com.botScofield.hireling.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class EmployeeDto {
    private Long employeeId;
    private String fullName;
    private String email;
    private String role;
    private DepartmentDto department;

}