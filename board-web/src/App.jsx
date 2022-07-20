import { Outlet } from "react-router-dom";
import LayoutHeader from "./component/layout/LayoutHeader";
import React, { useEffect } from "react";
import useSWR from "swr";
import { API_URL } from "./constants/constants";
import { getFetcher } from "./utils/fetcher-utils";
import {useDispatch, useSelector} from "react-redux";
import "react-toastify/dist/ReactToastify.css";
import { useCookies } from "react-cookie";
import authSlice from "./features/authSlice";

// TODO : 모달창 만들기

function App() {
  const { isAuth, accessToken, refreshToken } = useSelector((state) => state.auth);
  const [accessCookies, setAccessCookies] = useCookies(["accessToken"]);
  const [refreshCookies, setRefreshCookies] = useCookies(["refreshToken"]);
  const accessCookie = accessCookies.accessToken;
  const refreshCookie = refreshCookies.refreshToken;

  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(setAccessCookies(accessToken))
  }, []);

  // const { data, error } = useSWR(isAuth ? API_URL : null, getFetcher);
  // if (error) {
  //   return ;
  // }
  // if (data) {
  //   console.log(data);
  // }

  return (
    <div className="App">
      <LayoutHeader></LayoutHeader>
      <main>
        <Outlet />
      </main>
    </div>
  );
}

export default App;
