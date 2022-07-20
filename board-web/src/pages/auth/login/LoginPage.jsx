import React from "react";
import { useForm } from "react-hook-form";
import { Link } from "react-router-dom";
import { postFetcher } from "../../../utils/fetcher-utils";
import { useDispatch, useSelector } from "react-redux";
import { API_URL } from "../../../constants/constants";
import { login } from "../../../features/authSlice";
import { toast, ToastContainer } from "react-toastify";
import { Navigate } from "react-router";

function LoginPage() {
  const dispatch = useDispatch();
  const alertMsg = () => {
    toast("에러");
  };
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  const { isAuth } = useSelector((state) => state.auth);

  if (isAuth) {
    return <Navigate to={"/"}></Navigate>;
  }

  const onSubmit = (data) => {
    postFetcher(API_URL + "/login", data).then((res) => {
      dispatch(login(res));
    });
  };

  // TODO : 에러 반환 Toastify로 처리
  return (
    <>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="border-1 p-2 shadow">
          <input {...register("username", { required: true })} className={"input_text"} placeholder={"아이디"} />
          <input
            {...register("password", { required: true })}
            type={"password"}
            placeholder={"비밀번호"}
            className={"input_password"}
            autoComplete={"off"}
          />
        </div>
        {(errors.username?.type === "required" || errors.password?.type === "required") && alertMsg("errors")}
        <input type="submit" value="로그인" className="confirm-btn" />
      </form>
      <div className=" text-center">
        <Link to={"findid"} className="sub_copy">
          아이디 찾기
        </Link>{" "}
        <span> | </span>
        <Link to={"findpassword"} className="sub_copy">
          비밀번호 찾기
        </Link>{" "}
        <span> | </span>
        <Link to={"/auth/register"} className="sub_copy">
          회원가입
        </Link>
        <ToastContainer></ToastContainer>
      </div>
    </>
  );
}

export default LoginPage;
