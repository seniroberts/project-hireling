package com.botScofield.hireling.service.interfaces;

import com.botScofield.hireling.dto.EmployeeDto;
import com.botScofield.hireling.model.EmployeeModel;

import java.util.List;

public interface EmployeeServiceInterface {
    public EmployeeModel createEmployee(EmployeeModel employeeModel);
    public List<EmployeeModel> getAllEmployeesFromDB();
    public List<EmployeeDto> getAllEmployees();
    public EmployeeDto getEmployee(Long employeeId);
    public EmployeeModel updateEmployeeDetails(Long employeeId, EmployeeModel employeeModel);
    public Object deleteEmployee(Long employeeId);
    public boolean isEmailUnique(String email);

}