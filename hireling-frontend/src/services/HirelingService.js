/* eslint-disable import/no-anonymous-default-export */
import axios from "axios";
import { appStrings } from "../utils/Strings";

class HirelingService {
	getAllEmployees() {
		return axios.get(appStrings.config.EMPLOYEE_API_BASE_URL);
	}

	createEmployee(employeeData) {
		return axios.post(appStrings.config.CREATE_EMPLOYEE_URL, employeeData);
	}

	updateEmployeeDetails(employeeId, employeeData) {
		return axios.post(
			`${appStrings.config.UPDATE_EMPLOYEE_URL}/${employeeId}`,
			employeeData
		);
	}

	deleteEmployee(employeeId) {
		return axios.delete(
			`${appStrings.config.DELETE_EMPLOYEE_URL}/${employeeId}`
		);
	}

	getEmployee(employeeId) {
		return axios.get(
			`${appStrings.config.EMPLOYEE_API_BASE_URL}/${employeeId}`
		);
	}

	getDepartments() {
		return axios.get(appStrings.config.DEPARTMENT_API_BASE_URL);
	}

	updateDepartmentDetails(departmentId, departmentData) {
		return axios.post(
			`${appStrings.config.UPDATE_DEPARTMENT_URL}/${departmentId}`,
			departmentData
		);
	}

	deleteDepartment(departmentId) {
		return axios.delete(
			`${appStrings.config.DELETE_DEPARTMENT_URL}/${departmentId}`
		);
	}

	createDepartment(departmentData) {
		return axios.post(appStrings.config.CREATE_DEPARTMENT_URL, departmentData);
	}

	getDepartment(departmentId) {
		return axios.get(
			`${appStrings.config.DEPARTMENT_API_BASE_URL}/${departmentId}`
		);
	}
}

export default new HirelingService();
