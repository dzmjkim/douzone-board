import React from 'react';
import {Navigate} from "react-router";
import {useSelector} from "react-redux";

function AdminPage(props) {

	// TODO : isAuth, user role = admin
	const {isAuth} = useSelector((state) => state.auth)
	if (!isAuth){
		return <Navigate to={"/auth/login"} replace></Navigate>
	}
	return (
		<div>

		</div>
	);
}

export default AdminPage;