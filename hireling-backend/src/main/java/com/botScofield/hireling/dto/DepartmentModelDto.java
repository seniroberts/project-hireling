package com.botScofield.hireling.dto;

import com.botScofield.hireling.model.EmployeeModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DepartmentModelDto {

    private Long departmentId;
    private String departmentName;
    private Long costCode;
    private List<EmployeeModel> employees = new ArrayList<>();
    private int EmployeeCount;
}