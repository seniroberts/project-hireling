import React from "react";
import { Link } from "react-router-dom";
import { appStrings } from "../utils/Strings";

export default function HomePage() {
	return (
		<div className="card text-center mt-5">
			<div className="card-header">
				<ul className="nav nav-pills card-header-pills">
					<li className="nav-item">
						<Link className="nav-link active" to={"/"}>
							{appStrings.buttonsText.home}
						</Link>
					</li>
					<li className="nav-item">
						<Link className="nav-link" to={"/departments"}>
							{appStrings.departments}
						</Link>
					</li>
					<li className="nav-item">
						<Link
							className="nav-link"
							to={"/employees"}
							tabindex="-1"
							aria-disabled="true">
							{appStrings.employees}
						</Link>
					</li>
				</ul>
			</div>
			<div className="card-body">
				<h5 className="card-title">{appStrings.welcomeText}</h5>
				<p className="card-text">{appStrings.bodyText}</p>
				<a href="/" className="btn btn-primary">
					{appStrings.buttonsText.explore}
				</a>
			</div>
		</div>
	);
}
