package com.botScofield.hireling.service.implementation;

import com.botScofield.hireling.dto.DepartmentDto;
import com.botScofield.hireling.dto.DepartmentModelDto;
import com.botScofield.hireling.exceptions.notFound.DepartmentNotFoundException;
import com.botScofield.hireling.mapper.DepartmentMapper;
import com.botScofield.hireling.model.DepartmentModel;
import com.botScofield.hireling.repository.DepartmentRepository;
import com.botScofield.hireling.service.interfaces.DepartmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements DepartmentServiceInterface {


    private final DepartmentRepository departmentRepository;
    private final EmployeeService employeeService;
    @Autowired
    public DepartmentService(EmployeeService employeeService, DepartmentRepository departmentRepository) {
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentModel createDepartment(DepartmentModel departmentModel) {
        return departmentRepository.save(departmentModel);
    }

    @Override
    public List<DepartmentModelDto> getAllDepartments() {
        List<DepartmentModel> departmentModelList = departmentRepository.findAll();
        List<DepartmentModelDto> departmentModelDtoList = new ArrayList<>();
        for(DepartmentModel department: departmentModelList) {
            DepartmentMapper departmentMapper = new DepartmentMapper(employeeService);
            DepartmentModelDto departmentModelDto = departmentMapper.convertModelToDepartmentalDto(department);
            departmentModelDtoList.add(departmentModelDto);
        }
        return departmentModelDtoList;
    }

    @Override
    public List<DepartmentDto> getDepartmentsPlainView() {
        List<DepartmentModel> departmentModelList = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        for(DepartmentModel department: departmentModelList) {
            DepartmentMapper departmentMapper = new DepartmentMapper(employeeService);
            DepartmentDto departmentDto = departmentMapper.modelToDtoPlainView(department);
            departmentDtoList.add(departmentDto);
        }
        return departmentDtoList;
    }

    @Override
    public DepartmentModelDto getDepartment(Long departmentId) {
        Optional<DepartmentModel> optionalDepartment = departmentRepository.findById(departmentId);
        DepartmentModel departmentModel = new DepartmentModel();
        if(optionalDepartment.isPresent()){
            departmentModel = optionalDepartment.get();
        }
        DepartmentMapper departmentMapper = new DepartmentMapper(employeeService);
        return departmentMapper.convertModelToDepartmentalDto(departmentModel);
    }

    @Override
    public DepartmentModel updateDepartmentDetails(Long departmentId, DepartmentModel departmentModel) {
        return departmentRepository.findById(departmentId).map(departmentToUpdate -> {
            departmentToUpdate.setDepartmentName(departmentModel.getDepartmentName());
            departmentToUpdate.setCostCode(departmentModel.getCostCode());
            return departmentRepository.save(departmentToUpdate);
        }).orElseThrow(() -> new DepartmentNotFoundException(departmentId));
    }

    @Override
    public Object deleteDepartment(Long departmentId)  {
        departmentRepository.deleteById(departmentId);
        return null;
    }

    @Override
    public DepartmentModel getDepartmentModel(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }
}