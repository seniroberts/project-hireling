import React, { useEffect, useState } from "react";
import HirelingService from "../services/HirelingService";
import { Link, useNavigate } from "react-router-dom";
import { appStrings } from "../utils/Strings";

export default function ListEmployees() {
	const [employeesList, setEmployeesList] = useState([]);
	const navigate = useNavigate();

	const getAllEmployees = () => {
		HirelingService.getAllEmployees().then((response) => {
			setEmployeesList(response.data.Content);
		});
	};

	const deleteEmployee = (e, employeeId) => {
		e.preventDefault();
		HirelingService.deleteEmployee(employeeId)
			.then(navigate(`/employees`))
			.catch((error) => console.log(error));
		getAllEmployees();
	};

	useEffect(() => {
		getAllEmployees();
	}, []);

	return (
		<div>
			<div className="container">
				<Link to={"/"} className="btn btn-primary mb-2 mt-3">
					{appStrings.buttonsText.home}
				</Link>{" "}
				<Link
					to={"/employees/create"}
					className="btn btn-primary mb-2 mt-3"
					href="/home">
					{appStrings.buttonsText.addEmployee}
				</Link>
				<h2 className="text-center mb-4">{appStrings.employees}</h2>
				<table className="table table-bordered table striped">
					<thead>
						<th>{appStrings.employeeTableHeader.employeeId}</th>
						<th>{appStrings.employeeTableHeader.fullname}</th>
						<th>{appStrings.employeeTableHeader.emailAddress}</th>
						<th>{appStrings.employeeTableHeader.role}</th>
						<th>{appStrings.employeeTableHeader.department}</th>
						<th>{appStrings.employeeTableHeader.actions}</th>
					</thead>
					<tbody>
						{employeesList.map((employee) => (
							<tr id={employee.employeeId}>
								<td>{employee.employeeId}</td>
								<td>{employee.fullName}</td>
								<td>{employee.email}</td>
								<td>{employee.role}</td>
								<td>
									{employee.department === null
										? " "
										: employee["department"]["departmentName"]}
								</td>
								<td>
									<Link
										to={`/employees/update/${employee.employeeId}`}
										className="btn btn-info me-1">
										{appStrings.buttonsText.update}
									</Link>{" "}
									<Link
										to={"/employees"}
										className="btn btn-danger me-1"
										onClick={(e) => deleteEmployee(e, employee.employeeId)}>
										{appStrings.buttonsText.delete}
									</Link>{" "}
									<Link
										to={`/employees/${employee.employeeId}`}
										className="btn btn-success">
										{appStrings.buttonsText.view}
									</Link>
								</td>
							</tr>
						))}
					</tbody>
				</table>
			</div>
		</div>
	);
}
