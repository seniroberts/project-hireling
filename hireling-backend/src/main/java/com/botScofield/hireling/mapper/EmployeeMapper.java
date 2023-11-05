package com.botScofield.hireling.mapper;

import com.botScofield.hireling.dto.DepartmentDto;
import com.botScofield.hireling.dto.DepartmentModelDto;
import com.botScofield.hireling.dto.EmployeeDto;
import com.botScofield.hireling.model.EmployeeModel;
import com.botScofield.hireling.service.implementation.DepartmentService;
import com.botScofield.hireling.service.implementation.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class EmployeeMapper {
    static String emailSuffix = "@hireling.com";
    private final DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    public EmployeeMapper(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    public EmployeeDto convertModelToDto(EmployeeModel employeeModel) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employeeModel.getEmployeeId());
        employeeDto.setFullName(employeeModel.getFirstname() + " " + employeeModel.getLastname());

        String firstLetterOfFirstName = employeeModel.getFirstname().substring(0,1);
        if(employeeModel.getEmail() == null){
            employeeDto.setEmail(firstLetterOfFirstName + "." + employeeModel.getLastname() + emailSuffix);
        } else {
            employeeDto.setEmail(employeeModel.getEmail());
        }

        employeeDto.setRole(employeeModel.getRole());
        if(employeeModel.getDepartmentId() != null){
            Optional<DepartmentModelDto> departmentModelOptional = Optional.ofNullable(
                    departmentService.getDepartment(employeeModel.getDepartmentId()));
            if(departmentModelOptional.isPresent()){
                DepartmentModelDto departmentModel = departmentModelOptional.get();
                DepartmentDto departmentDto = new DepartmentDto();
                departmentDto.setDepartmentName(departmentModel.getDepartmentName());
                departmentDto.setDepartmentId(departmentModel.getDepartmentId());
                employeeDto.setDepartment(departmentDto);
            }
        }

        return employeeDto;
    }

}