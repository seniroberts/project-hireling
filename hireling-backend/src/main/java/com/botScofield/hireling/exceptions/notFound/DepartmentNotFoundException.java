package com.botScofield.hireling.exceptions.notFound;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(Long departmentId) {
        super("Department with id: " + departmentId + " not found");
    }

    public DepartmentNotFoundException(String errorMessage, Long departmentId, Throwable cause) {
        super((errorMessage));
    }
}