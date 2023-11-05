package com.botScofield.hireling.service.interfaces;

import com.botScofield.hireling.dto.DepartmentDto;
import com.botScofield.hireling.dto.DepartmentModelDto;
import com.botScofield.hireling.model.DepartmentModel;

import java.util.List;

public interface DepartmentServiceInterface {
    public DepartmentModel createDepartment(DepartmentModel departmentModel);
    public List<DepartmentModelDto> getAllDepartments();
    public List<DepartmentDto> getDepartmentsPlainView();
    public DepartmentModelDto getDepartment(Long departmentId);
    public DepartmentModel updateDepartmentDetails(Long departmentId, DepartmentModel departmentModel);
    public Object deleteDepartment(Long departmentId);
    public DepartmentModel getDepartmentModel(Long departmentId);

}