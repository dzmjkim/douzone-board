import React from "react";
import { useForm } from "react-hook-form";


// TODO : 에러 메시지 state로 전달
function AnonymityPage() {
  const {
    register,
    formState: { errors },
    handleSubmit,
  } = useForm();

  return (
    <form onSubmit={handleSubmit()} className={"m-5 flex flex-col"}>
      <div className="text-2xl text-center">익명의 소리</div>
      <div className="sub_copy">반 내에 불만사항 및 개선사항을 적어주세요.</div>
      <input
        {...register("mailContent", { required: true, minLength: 20 })}
        name="mailContent"
        className={"h-[10vh] border-gray-200 border-[1px]"}
        placeholder={"최소 20글자 이상"}
      ></input>
      {errors.mailContent?.type === "required" && <div className="validation-error">필수 항목 입니다.</div>}
      {errors.mailContent?.type === "minLength" && <div className="validation-error">20글자 이상 적으세요.</div>}
      <input type="submit" value="보내기" className={"confirm-btn"} />
    </form>
  );
}

export default AnonymityPage;
