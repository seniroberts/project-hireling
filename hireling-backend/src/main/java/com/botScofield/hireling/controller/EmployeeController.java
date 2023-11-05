package com.botScofield.hireling.controller;

import com.botScofield.hireling.customResponse.CustomResponseHandler;
import com.botScofield.hireling.dto.DepartmentDto;
import com.botScofield.hireling.dto.EmployeeDto;
import com.botScofield.hireling.exceptions.error.InternalServerErrorException;
import com.botScofield.hireling.model.EmployeeModel;
import com.botScofield.hireling.service.implementation.DepartmentService;
import com.botScofield.hireling.service.implementation.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@CrossOrigin("http://localhost:3000")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<Object> createEmployee(@RequestBody EmployeeModel employeeModel){
        if(employeeService.isEmailUnique(employeeModel.getEmail())){
               // Save Employee
                return CustomResponseHandler.customResponseBuilder(
                "Employee Created Successfully",
                HttpStatus.CREATED,
                employeeService.createEmployee(employeeModel)
            );
        } else {
            return ResponseEntity.badRequest().body("Email Address Already In Use");
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllEmployees(){
          return  CustomResponseHandler.customResponseBuilder(
                  "All Employees",
                  HttpStatus.OK,
                  employeeService.getAllEmployees()
          );
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Object> getEmployee(@PathVariable ("employeeId") Long employeeId){
        return CustomResponseHandler.customResponseBuilder(
                "Employee Details",
                HttpStatus.OK,
                employeeService.getEmployee(employeeId)
        );
    }

    @PostMapping("/update/{employeeId}")
    public ResponseEntity<Object> updateEmployeeDetails (@PathVariable ("employeeId") Long employeeId,
                                                         @RequestBody EmployeeModel employeeModel){
        if(employeeService.isEmailUnique(employeeModel.getEmail())){
            return CustomResponseHandler.customResponseBuilder(
                    "Employee Details Updated Successfully",
                    HttpStatus.OK,
                    employeeService.updateEmployeeDetails(employeeId, employeeModel)
            );
        }
        else {
            return ResponseEntity.badRequest().body("Email Address Already In Use");
        }
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable ("employeeId") Long employeeId){

        return CustomResponseHandler.customResponseBuilder(
                "Employee Details Deleted Successfully",
                HttpStatus.OK,
                employeeService.deleteEmployee(employeeId)
        );
    }
}