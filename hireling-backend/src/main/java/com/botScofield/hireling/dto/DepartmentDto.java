package com.botScofield.hireling.dto;

import com.botScofield.hireling.model.EmployeeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data

public class DepartmentDto {
    private Long departmentId;
    private String departmentName;
    private Long costCode;
}