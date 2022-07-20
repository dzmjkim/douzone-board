import React, { useRef } from "react";
import { useForm } from "react-hook-form";

// import { API_URL } from "../../../constants/constants";

function RegisterPage() {
  const {
    register,
    formState: { errors },
    handleSubmit,
    watch,
  } = useForm();

  const password = useRef({});
  password.current = watch("passwordCheck");

  // const onSubmit = (data) => {
  //   axiosPost(API_URL + "/register", data).then((res) => {
  //     console.log(res);
  //   });
  // };

  // TODO : 반 버튼으로 설정
  // TODO : 에러 메시지 모으기
  return (
    <form onSubmit={handleSubmit()} className={"m-5 flex flex-col"}>
      <label>아이디</label>
      <input {...register("username", { required: true, minLength: 4 })} />
      {errors.username?.type === "required" && <div className="text-red-500">필수 항목 입니다.</div>}
      {errors.username?.type === "minLength" && (
        <div className="text-red-500">아이디는 최소 4글자 이상이어야 합니다.</div>
      )}
      <label>비밀번호</label>
      <input
        {...register("password", { required: true, minLength: 8 })}
        type={"password"}
        autoComplete={"off"}
      />
      {errors.password?.type === "required" && <div className="text-red-500">필수 항목 입니다.</div>}
      {errors.password?.type === "minLength" && (
        <div className="text-red-500">비밀번호는 최소 8글자 이상이어야 합니다.</div>
      )}
      <label>비밀번호 확인</label>
      <input
        {...register("passwordCheck", { validate: (value) => value === password.current })}
        type={"password"}

        autoComplete={"off"}
      />
      {errors.passwordCheck?.type === "required" && <div className="text-red-500">필수 항목 입니다.</div>}
      {errors.passwordCheck?.type === "validate" && <div className="text-red-500">비밀번호가 일치하지 않습니다.</div>}
      <label>이름</label>
      <input {...register("name", { required: true })}/>
      {errors.name?.type === "required" && <div className="text-red-500">필수 항목 입니다.</div>}
      <label>반</label>
      <input {...register("userClass", { required: true })} />
      {errors.userClass?.type === "required" && <div className="text-red-500">필수 항목 입니다.</div>}
      <input type="submit" value="회원가입" className={"confirm-btn"} />
    </form>
  );
}

export default RegisterPage;
