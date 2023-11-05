import React, { useEffect, useState } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import { appStrings } from "../utils/Strings";
import HirelingService from "../services/HirelingService";
import { ToastContainer, toast } from "react-toastify";
import Options from "./Options";
import "react-toastify/dist/ReactToastify.css";

export default function CreateEmployee() {
	const [firstname, setFirstname] = useState("");
	const [lastname, setLastname] = useState("");
	const [email, setEmail] = useState("");
	const [role, setRole] = useState("");
	const [departmentId, setDepartmentId] = useState(0);
	const [departmentList, setDepartmentList] = useState([]);

	const { employeeId } = useParams();
	const navigate = useNavigate();

	const employeeData = { firstname, lastname, email, role, departmentId };
	const emailSuffix = appStrings.emailSuffix;

	const notify = (message) => {
		toast.error(message);
	};

	useEffect(() => {
		if (employeeId) {
			HirelingService.getEmployee(employeeId)
				.then((response) => {
					setFirstname(response.data.Content.firstname);
					setLastname(response.data.Content.lastname);
					setEmail(response.data.Content.email);
					setRole(response.data.Content.role);
					setDepartmentId(response.data.Content.department);
				})

				.catch((error) => console.log(error));
		}
	}, [employeeId]);

	useEffect(() => {
		getDepartments();
	}, []);

	const onSaveEmployee = (e) => {
		e.preventDefault();

		if (
			firstname !== undefined &&
			lastname !== undefined &&
			email !== "" &&
			role !== "" &&
			departmentId !== null
		) {
			if (employeeId) {
				HirelingService.updateEmployeeDetails(employeeId, employeeData)
					.then(navigate("/employees"))
					.catch((error) => console.log(error));
			} else {
				HirelingService.createEmployee(employeeData)
					.then(navigate("/employees"))
					.catch((err) => console.log(err));
			}
		} else {
			notify(appStrings.notification.missingFieldsNotification);
		}
	};

	const getDepartments = () => {
		HirelingService.getDepartments().then((response) => {
			setDepartmentList(response.data.Content);
		});
	};

	const buildEmail =
		firstname === "." || firstname === undefined
			? " "
			: firstname[0] + "." + lastname + emailSuffix;

	const handleDropdownSelection = (e) => {
		setDepartmentId(e.target.value);
	};

	const getCardHeaderText = () => {
		return employeeId ? appStrings.updateEmployee : appStrings.createEmployee;
	};

	useEffect(() => {
		if (firstname !== "") {
			setEmail(buildEmail);
		}
	}, [buildEmail, emailSuffix, firstname, lastname]);

	return (
		<div>
			<div className="container mt-5">
				<div className="row">
					<div className="card col-md-6 offset-md-3">
						<h2 className="text-center">{getCardHeaderText()}</h2>
						<div className="card-body">
							<form>
								<div className="form-group mb-2">
									<input
										className="form-control"
										value={firstname}
										onChange={(e) => setFirstname(e.target.value)}
										type="text"
										placeholder="Enter First Name"
									/>
								</div>
								<div className="form-group mb-2">
									<input
										className="form-control"
										value={lastname}
										onChange={(e) => setLastname(e.target.value)}
										type="text"
										placeholder="Enter Last Name"
									/>
								</div>
								<div className="form-group mb-2">
									<input
										className="form-control"
										value={email}
										readOnly
										type="text"
										placeholder="Enter Email"
									/>
								</div>
								<div className="form-group mb-2">
									<input
										className="form-control"
										value={role}
										onChange={(e) => setRole(e.target.value)}
										type="text"
										placeholder="Enter Role"
									/>
								</div>
								<div className="form-group mb-2">
									<Options
										handleSelection={handleDropdownSelection}
										listOfOptions={departmentList}
									/>
								</div>
								<button
									className="btn btn-success"
									onClick={(e) => onSaveEmployee(e)}>
									{appStrings.save}
								</button>{" "}
								<Link to={"/employees"} className="btn btn-danger" href="/">
									{appStrings.cancel}
								</Link>
							</form>
							<ToastContainer
								position="top-right"
								autoClose={5000}
								hideProgressBar={false}
								newestOnTop={false}
								closeOnClick
								rtl={false}
								pauseOnFocusLoss={false}
								draggable
								pauseOnHover
								theme="dark"
							/>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
}
