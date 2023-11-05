package com.botScofield.hireling.controller;

import com.botScofield.hireling.customResponse.CustomResponseHandler;
import com.botScofield.hireling.dto.DepartmentDto;
import com.botScofield.hireling.dto.DepartmentModelDto;
import com.botScofield.hireling.model.DepartmentModel;
import com.botScofield.hireling.service.implementation.DepartmentService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@CrossOrigin("http://localhost:3000")

public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<Object> createDepartment(@RequestBody DepartmentModel departmentModel){
        return CustomResponseHandler.customResponseBuilder(
                "Department Created Successfully",
                HttpStatus.CREATED,
                departmentService.createDepartment(departmentModel)
        );
    }

    @GetMapping
    public ResponseEntity<Object> getAllDepartments(){
        return CustomResponseHandler.customResponseBuilder(
                "All Departments",
                HttpStatus.OK,
                departmentService.getAllDepartments()
        );
    }

    @GetMapping("/basic-view")
    public ResponseEntity<Object> getDepartmentsPlainView(){return
            CustomResponseHandler.customResponseBuilder(
                    "Department Details [Plain DB View]",
                    HttpStatus.OK,
                    departmentService.getDepartmentsPlainView()
            );
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Object> getDepartment(@PathVariable ("departmentId") Long departmentId){
        return CustomResponseHandler.customResponseBuilder(
                "Department Details Retrieved Successfully",
                HttpStatus.OK,
                departmentService.getDepartment(departmentId)
        );
    }

    @PostMapping("/update/{departmentId}")
    public ResponseEntity<Object> updateDepartmentDetails(@PathVariable ("departmentId") Long departmentId,
                                                          @RequestBody DepartmentModel departmentModel){
        return CustomResponseHandler.customResponseBuilder(
                "Department Updated Successfully",
                HttpStatus.OK,
                departmentService.updateDepartmentDetails(departmentId, departmentModel)

        );
    }

    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable ("departmentId") Long departmentId){
        return CustomResponseHandler.customResponseBuilder(
                "Department Deleted Successfully",
                HttpStatus.OK,
                departmentService.deleteDepartment(departmentId)
        );
    }

}