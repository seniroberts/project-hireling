package com.botScofield.hireling;

import com.botScofield.hireling.model.DepartmentModel;
import com.botScofield.hireling.model.EmployeeModel;
import com.botScofield.hireling.repository.DepartmentRepository;
import com.botScofield.hireling.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class HirelingApplication {

	public static void main(String[] args) {
			SpringApplication.run(HirelingApplication.class, args);}

	@Bean
	CommandLineRunner run(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository){
		// Adding Departments:
		String[] departmentNames = {"Finance", "Engineering", "HR", "Sales", "Research"};
		return args -> {
            for (String departmentName : departmentNames) {
                DepartmentModel department = new DepartmentModel();
                department.setDepartmentName(departmentName);
                departmentRepository.save(department);
            }

			// Adding Employees:
			String[] firstNames = {"John", "Lara", "Clark", "Ben", "Louise"};
			String[] lastNames = {"Doe", "Croft", "Kent", "Burton", "Lane"};
			String[] roles = {"Accountant", "HR Analyst", "Software Engineer", "Sales Officer", "Research Analyst"};
			for (int j = 0; j < firstNames.length; j++){
				EmployeeModel employee = new EmployeeModel();
				employee.setFirstname(firstNames[j]);
				employee.setLastname(lastNames[j]);
				employee.setRole(roles[j]);
				employeeRepository.save(employee);
			}
		};


	}



}