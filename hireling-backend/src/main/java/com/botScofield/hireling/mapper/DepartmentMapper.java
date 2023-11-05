package com.botScofield.hireling.mapper;

import com.botScofield.hireling.dto.DepartmentDto;
import com.botScofield.hireling.dto.DepartmentModelDto;
import com.botScofield.hireling.dto.EmployeeDto;
import com.botScofield.hireling.model.DepartmentModel;
import com.botScofield.hireling.model.EmployeeModel;
import com.botScofield.hireling.service.implementation.DepartmentService;
import com.botScofield.hireling.service.implementation.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DepartmentMapper {


    private final EmployeeService employeeService;

    @Autowired
    public DepartmentMapper(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public DepartmentDto modelToDtoPlainView(DepartmentModel departmentModel){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentId(departmentModel.getDepartmentId());
        departmentDto.setDepartmentName(departmentModel.getDepartmentName());
        departmentDto.setCostCode(departmentModel.getCostCode());

        return departmentDto;
    }

    public DepartmentModelDto convertModelToDepartmentalDto(DepartmentModel departmentModel) {
        DepartmentModelDto departmentModelDto = new DepartmentModelDto();
        departmentModelDto.setDepartmentId(departmentModel.getDepartmentId());
        departmentModelDto.setDepartmentName(departmentModel.getDepartmentName());
        departmentModelDto.setCostCode(departmentModel.getCostCode());

        List<EmployeeModel> allEmployeesFromDB = employeeService.getAllEmployeesFromDB();
        List<EmployeeModel> employeesList = new ArrayList<>();

        for (EmployeeModel employeeModel : allEmployeesFromDB) {
            if (Objects.equals(employeeModel.getDepartmentId(), departmentModelDto.getDepartmentId())) {
                employeesList.add(employeeModel);
            }
        }

        departmentModelDto.setEmployees(employeesList);
        departmentModelDto.setEmployeeCount(departmentModelDto.getEmployees().size());

        return departmentModelDto;

    }

}