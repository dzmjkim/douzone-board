import React from "react";
import { Link } from "react-router-dom";
import { ReactComponent as Community } from "../../assets/icon/book-open-solid.svg";
import { ReactComponent as Anonymity } from "../../assets/icon/paper-plane-solid.svg";

function DomesticPage() {

  return (
    <div className={"grid min-w-max grid-cols-2 md:grid-cols-4"}>
      <Link to={"anonymity"} className={"main_menu_btn"}>
          <Community className={"main_menu_icon_btn"} />
          <div className="sub_copy">익명의 소리함</div>

      </Link>
      <Link to={"board"} className={"main_menu_btn"}>
        <Anonymity className={"main_menu_icon_btn"}/>
        <div className="sub_copy">자유 게시판</div>
      </Link>

    </div>
  );
}

export default DomesticPage;
