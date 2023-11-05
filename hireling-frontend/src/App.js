import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import ListEmployees from "./components/ListEmployees";
import Header from "./components/Header";
import Footer from "./components/Footer";
import CreateEmployee from "./components/CreateEmployee";
import ViewEmployee from "./components/ViewEmployee";
import HomePage from "./components/HomePage";
import ListDepartments from "./components/ListDepartments";
import ViewDepartment from "./components/ViewDepartment";
import CreateDepartment from "./components/CreateDepartment";

function App() {
	return (
		<BrowserRouter>
			<Header />
			<div className="container">
				<Routes>
					<Route path="/" element={<HomePage />} />
					<Route path="/" element={<ListEmployees />} />
					<Route path="/employees" element={<ListEmployees />} />
					<Route path="/employees/create" element={<CreateEmployee />} />
					<Route
						path="/employees/update/:employeeId"
						element={<CreateEmployee />}
					/>
					<Route path="/employees/:employeeId" element={<ViewEmployee />} />
					<Route path="/departments" element={<ListDepartments />} />
					<Route
						path="/departments/:departmentId"
						element={<ViewDepartment />}
					/>
					<Route path="/departments/create" element={<CreateDepartment />} />
					<Route
						path="/departments/update/:departmentId"
						element={<CreateDepartment />}
					/>
					<Route path="/departments/create" element={<ListDepartments />} />
					<Route
						path="/departments/update/:departmentId"
						element={<ListDepartments />}
					/>
				</Routes>
			</div>
			<Footer />
		</BrowserRouter>
	);
}

export default App;
