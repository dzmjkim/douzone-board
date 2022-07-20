import React from "react";
import {useSelector} from "react-redux";
import {Navigate} from "react-router";

function BoardPage() {

  const {isAuth} = useSelector((state) => state.auth)

  // Navigation Guard
  if (!isAuth){
    return <Navigate to={"/auth/login"} replace></Navigate>
    // return <Navigate to={"auth/login"} replace></Navigate>
  }

  return <div>
    로그인 완료!
  </div>;
}

export default BoardPage;
