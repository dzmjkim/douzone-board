import React from "react";
// import {} from "react-router-dom";
import { useNavigate } from "react-router";

function NotFoundPage() {
  const navigate = useNavigate();
  function handleClick() {
    navigate(-1);
  }
  return (
    <div className={"flex flex-col justify-center"}>
      <div className="text-2xl text-douzone text-center">404 NOT FOUND</div>
      <button type="button" onClick={handleClick} className={" confirm-btn"}>
        뒤로 가기
      </button>
    </div>
  );
}

export default NotFoundPage;
