/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import HirelingService from "../services/HirelingService";
import { appStrings } from "../utils/Strings";

export default function ViewEmployee() {
	const [employee, setEmployee] = useState({
		fullName: "",
		email: "",
		role: "",
		department: "",
	});

	const { employeeId } = useParams();

	const getEmployee = async () => {
		await HirelingService.getEmployee(employeeId).then((response) =>
			setEmployee(response.data.Content)
		);
	};

	useEffect(() => {
		getEmployee();
	}, [getEmployee]);

	return (
		<div className="container">
			<div className="row">
				<div className="col-md-6 offset-md-3 border rounded p-4 mt-4 shadow">
					<h2 className="text-center m-4">{appStrings.employeeDetails}</h2>

					<div className="card">
						<div className="card-header ">
							<h6
								className="class-header text-center mb-4 mt-2"
								style={{ color: "rebeccapurple" }}>
								{appStrings.detailsOfEmployees}: {employeeId}
							</h6>
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<b>{appStrings.fullName}: </b>
									{employee.fullName}
								</li>
								<li class="list-group-item">
									<b>{appStrings.emailAddress}: </b>
									{employee.email}
								</li>
								<li class="list-group-item">
									<b>{appStrings.role}: </b>
									{employee.role}
								</li>
								<li class="list-group-item">
									<b>{appStrings.department}: </b>
									{employee.department === null
										? " "
										: employee["department"]["departmentName"]}
								</li>
							</ul>
						</div>
					</div>
					<Link className="btn btn-primary mt-4 my-2" to={"/employees"}>
						{appStrings.backToEmployees}
					</Link>
				</div>
			</div>
		</div>
	);
}
