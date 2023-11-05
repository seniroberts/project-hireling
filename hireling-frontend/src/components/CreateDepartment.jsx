import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import HirelingService from "../services/HirelingService";
import { appStrings } from "../utils/Strings";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function CreateDepartment() {
	const [departmentName, setDepartmentName] = useState("");
	const [costCode, setCostCode] = useState(null);

	const { departmentId } = useParams();
	const navigate = useNavigate();

	const departmentDetails = { departmentName, costCode };

	const notify = (message) => {
		toast.error(message);
	};

	useEffect(() => {
		if (departmentId) {
			HirelingService.getDepartment(departmentId)
				.then((response) => {
					setDepartmentName(response.data.Content.departmentName);
					setCostCode(response.data.Content.costCode);
				})
				.catch((error) => console.log(error));
		}
	}, [departmentId]);

	const onSaveDepartment = (e) => {
		e.preventDefault();

		if (departmentName !== "") {
			if (departmentId) {
				HirelingService.updateDepartmentDetails(departmentId, departmentDetails)
					.then(navigate("/departments"))
					.catch((error) => console.log(error));
			} else {
				HirelingService.createDepartment(departmentDetails)
					.then(navigate("/departments"))
					.catch((error) => console.log(error));
			}
		} else {
			notify(appStrings.notification.departmentUpdateNotificationError);
		}
	};

	const getHeaderText = () => {
		return departmentId
			? appStrings.updateDepartment
			: appStrings.createDepartment;
	};

	return (
		<div>
			<div className="container mt-5">
				<div className="row">
					<div className="card col-md-6 offset-md-3">
						<h2 className="text-center">{getHeaderText()}</h2>
						<div className="card-body">
							<form>
								<div className="form-group mb-2">
									<input
										className="form-control"
										value={departmentName}
										onChange={(e) => setDepartmentName(e.target.value)}
										type="text"
										placeholder="Enter Department Name"
									/>
								</div>
								<div className="form-group mb-2">
									<input
										className="form-control"
										value={costCode}
										onChange={(e) => setCostCode(e.target.value)}
										type="text"
										placeholder="Enter Cost Code"
									/>
								</div>
								<button
									className="btn btn-success"
									onClick={(e) => onSaveDepartment(e)}>
									{appStrings.save}
								</button>{" "}
								<Link to={"/departments"} className="btn btn-danger" href="/">
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
