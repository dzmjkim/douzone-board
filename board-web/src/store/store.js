import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../features/authSlice";


// 방법 1
// const reducer = {
//   auth:authReducer
// }
//
// export const store = configureStore({reducer})

// 방법 2
export const store = configureStore({
  reducer: {
    auth: authReducer,
  },
});
