import React from "react";
import { appStrings } from "../utils/Strings";

export default function Options({ handleSelection, listOfOptions }) {
	return (
		<div>
			<select
				className="form-select"
				aria-label="Select Department"
				onChange={handleSelection}>
				<option defaultValue={0}>{appStrings.selectDepartment}</option>
				{listOfOptions.map((singleOption) => (
					<option value={singleOption.departmentId}>
						{singleOption.departmentName}
					</option>
				))}
			</select>
		</div>
	);
}
