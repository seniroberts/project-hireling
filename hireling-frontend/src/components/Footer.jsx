import React from "react";

export default function Footer() {
	return (
		<div>
			<footer className="footer">
				<span>
					All Right Reserved &copy; {new Date().getFullYear()} hireling Inc
				</span>
			</footer>
		</div>
	);
}
