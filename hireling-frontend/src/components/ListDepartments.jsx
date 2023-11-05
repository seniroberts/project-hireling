import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import HirelingService from "../services/HirelingService";
import { appStrings } from "../utils/Strings";

export default function ListDepartments() {
	const [departments, setDepartments] = useState([]);
	const navigate = useNavigate();

	useEffect(() => {
		getDepartments();
	}, []);

	const getDepartments = () => {
		HirelingService.getDepartments().then((response) => {
			setDepartments(response.data.Content);
		});
	};

	const deleteDepartment = (e, departmentId) => {
		e.preventDefault();
		HirelingService.deleteDepartment(departmentId)
			.then(navigate("/departments"))
			.catch((error) => console.log(error));
		getDepartments();
	};

	return (
		<div>
			<div className="container">
				<Link to={"/"} className="btn btn-success mb-2 mt-3">
					{appStrings.buttonsText.home}
				</Link>{" "}
				<Link
					to={"/departments/create"}
					className="btn btn-success mb-2 mt-3"
					href="/home">
					{appStrings.buttonsText.addDepartment}
				</Link>
				<h2 className="text-center mb-4">{appStrings.departments}</h2>
				<table className="table table-bordered table striped">
					<thead>
						<th>{appStrings.departmentTableHeader.departmentId}</th>
						<th>{appStrings.departmentTableHeader.departmentName}</th>
						<th>{appStrings.departmentTableHeader.numOfEmployees}</th>
						<th>{appStrings.departmentTableHeader.actions}</th>
					</thead>
					<tbody>
						{departments.map((department) => (
							<tr id={department.departmentId}>
								<td>{department.departmentId}</td>
								<td>{department.departmentName}</td>
								<td>{department.employeeCount}</td>
								<td>
									<Link
										to={`/departments/update/${department.departmentId}`}
										className="btn btn-info me-1">
										{appStrings.buttonsText.update}
									</Link>{" "}
									<Link
										to={"/departments"}
										className="btn btn-danger me-1"
										onClick={(e) =>
											deleteDepartment(e, department.departmentId)
										}>
										{appStrings.buttonsText.delete}
									</Link>{" "}
									<Link
										to={`/departments/${department.departmentId}`}
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
