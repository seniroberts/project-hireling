import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import HirelingService from "../services/HirelingService";
import { appStrings } from "../utils/Strings";

export default function ViewDepartment() {
	const [department, setDepartment] = useState({
		departmentName: "",
		employeeCount: "",
		costCode: "",
	});

	const { departmentId } = useParams();

	// eslint-disable-next-line react-hooks/exhaustive-deps
	const getDepartment = async () => {
		await HirelingService.getDepartment(departmentId).then((response) =>
			setDepartment(response.data.Content)
		);
	};

	useEffect(() => {
		getDepartment();
	}, [getDepartment]);

	return (
		<div className="container">
			<div className="row">
				<div className="col-md-6 offset-md-3 border rounded p-4 mt-4 shadow">
					<h2 className="text-center m-4">{appStrings.departmentDetails}</h2>

					<div className="card">
						<div className="card-header ">
							<h6
								className="class-header text-center mb-4 mt-2"
								style={{ color: "rebeccapurple" }}>
								{appStrings.departmentId}: {departmentId}
							</h6>
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<b>{appStrings.departmentName}: </b>
									{department.departmentName}
								</li>
								<li class="list-group-item">
									<b>{appStrings.costCode}: </b>
									{department.costCode}
								</li>
								<li class="list-group-item">
									<b>{appStrings.NumOfEmployees}: </b>
									{department.employeeCount}
								</li>
							</ul>
						</div>
					</div>
					<Link className="btn btn-primary mt-4 my-2" to={"/departments"}>
						{appStrings.backToDepartment}
					</Link>
				</div>
			</div>
		</div>
	);
}
