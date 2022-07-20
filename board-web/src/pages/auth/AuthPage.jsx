import React from 'react';
import {Outlet, useNavigate} from "react-router";
import {ReactComponent as Back} from "../../assets/icon/arrow-left-solid.svg";
import {Link} from "react-router-dom";
import {ReactComponent as DouzoneLogo} from "../../assets/lg-douzone-blue.svg";
import {ToastContainer} from "react-toastify";



// TODO : (isAuth && login)
function AuthPage() {

	const navigate = useNavigate();
	function handleClick() {
		navigate(-1);
	}
	// TODO : Navigation Guard
	// if (!isAuth){
		// return <Navigate to={"/auth/login"} replace={<LoginPage></LoginPage>}></Navigate>
		// return <Navigate to={"auth/login"} replace></Navigate>
	// }
	return (
		<div className={"m-5 flex flex-col"}>
			<Back className={"fill-douzone w-[20px]"} onClick={handleClick}></Back>
			<div className="flex justify-center mb-5">
				<Link to={"/"}>
					<DouzoneLogo className={"h-10 w-full p-2"}></DouzoneLogo>
				</Link>
			</div>
			<Outlet>
				<ToastContainer/>
			</Outlet>
		</div>
	);
}

export default AuthPage;