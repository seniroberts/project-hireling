package com.botScofield.hireling.exceptions.notFound;

public class EmployeeNotFoundException extends RuntimeException {
        public EmployeeNotFoundException(Long employeeId) {
            super("Employee with id: " + employeeId + " not found");
        }

        public EmployeeNotFoundException(String errorMessage, Long employeeId, Throwable cause) {
            super((errorMessage));
        }
}