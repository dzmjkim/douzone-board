import { createSlice } from "@reduxjs/toolkit";
import {} from "react-cookie";


const initialState = {
  // TODO : payload state 없애기 ( 객체 -> 객체 -> 속성 )
  payload:"",
  accessToken: "",
  refreshToken: "",
  isLoading: false,
  isAuth: false,
  errorMsg: "",
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    // TODO : 비동기 처리 (isLoading?)
    login(state, action) {
      state.payload = action.payload
      if (state.payload) {
        state.accessToken = state.payload.access_token;
        state.refreshToken = state.payload.refresh_token;
        state.isAuth = true;
        
      }
    },
    logout(state) {
      state.payload = "";
      state.accessToken = "";
      state.refreshToken = "";
      state.isAuth = false;
    },
    setAccessCookies(state,action){
      state.accessToken = action.payload
    },
    setRefreshCookies(state,action){
      state.refreshToken = action.payload
    }
  },
  // 비동기 처리 extraReducers
  extraReducers: {

  },
});

export default authSlice.reducer;
export const { login, logout } = authSlice.actions;
