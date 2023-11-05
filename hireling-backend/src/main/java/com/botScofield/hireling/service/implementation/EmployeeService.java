package com.botScofield.hireling.service.implementation;

import com.botScofield.hireling.dto.EmployeeDto;
import com.botScofield.hireling.exceptions.notFound.EmployeeNotFoundException;
import com.botScofield.hireling.mapper.EmployeeMapper;
import com.botScofield.hireling.model.EmployeeModel;
import com.botScofield.hireling.repository.EmployeeRepository;
import com.botScofield.hireling.service.interfaces.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    EmployeeRepository employeeRepository;

    private final DepartmentService departmentService;

    public boolean isEmailUnique(String email) {
        EmployeeModel existingUser = employeeRepository.findByEmail(email);
        return existingUser == null; // Return true if email is unique, false otherwise.
    }

    @Autowired
    public EmployeeService(@Lazy DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @Override
    public EmployeeModel createEmployee(EmployeeModel employeeModel) {
        return employeeRepository.save(employeeModel);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeModel> employeeModelList = employeeRepository.findAll();
        List<EmployeeDto> employeesDtoList = new ArrayList<>();
        for(EmployeeModel employee: employeeModelList){
            EmployeeMapper employeeMapper = new EmployeeMapper(departmentService);
            EmployeeDto employeeModelConvertedToDto = employeeMapper.convertModelToDto(employee);
            employeesDtoList.add(employeeModelConvertedToDto);
        }
        return employeesDtoList;
    }


    @Override
    public List<EmployeeModel> getAllEmployeesFromDB() {
      return employeeRepository.findAll();
    }

    @Override
    public EmployeeDto getEmployee(Long employeeId) {
        Optional<EmployeeModel> optionalEmployeeModel = employeeRepository.findById(employeeId);
        EmployeeMapper employeeMapper = new EmployeeMapper(departmentService);
        return employeeMapper.convertModelToDto(optionalEmployeeModel.get());
    }

    @Override
    public EmployeeModel updateEmployeeDetails(Long employeeId, EmployeeModel employeeModel) {
        return employeeRepository.findById(employeeId).map(employeeToUpdate -> {
            employeeToUpdate.setFirstname(employeeModel.getFirstname());
            employeeToUpdate.setLastname(employeeModel.getLastname());
            employeeToUpdate.setEmail(employeeModel.getEmail());
            employeeToUpdate.setRole(employeeModel.getRole());
            employeeToUpdate.setDepartmentId(employeeModel.getDepartmentId());
            return employeeRepository.save(employeeToUpdate);
        }).orElseThrow(() -> new EmployeeNotFoundException(employeeId));
    }

    @Override
    public Object deleteEmployee(Long employeeId) { employeeRepository.deleteById(employeeId);
        return null;
    }

}