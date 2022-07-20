import React from "react";
import { ReactComponent as DouzoneWhite } from "../../assets/lg-douzone-white.svg";

import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { logout } from "../../features/authSlice";
import {Navigate, useNavigate} from "react-router";

function LayoutHeader() {
  const { isAuth } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // TODO : useRef 등으로 처리..
  const onSubmit = () => {
    dispatch(logout());
    navigate('/')

  };
  return (
    // TODO : 햄버거 메뉴, 로그인 로그아웃 체크
    // TODO : 사이드바, 컴포넌트 레이지로딩
    <>
      <nav className="flex flex-wrap items-center justify-between border-b border-douzone bg-douzone p-6">
        <Link to={"/"}>
          <DouzoneWhite></DouzoneWhite>
        </Link>
        {isAuth ? (
          <div onClick={onSubmit}>로그아웃</div>
        ) : (
          <div className="text-white">
            <Link to={"auth/login"} className="">
              로그인
            </Link>
          </div>
        )}
      </nav>
    </>
  );
}

export default LayoutHeader;
