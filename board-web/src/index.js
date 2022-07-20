import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { Route, Routes } from "react-router";
import DomesticPage from "./pages/domestic/DomesticPage";
import AnonymityPage from "./pages/anonymity/AnonymityPage";
import NotFoundPage from "./pages/error/NotFoundPage";
import RegisterPage from "./pages/auth/register/RegisterPage";
import LoginPage from "./pages/auth/login/LoginPage";
import { Provider } from "react-redux";
import { store } from "./store/store";
import BoardPage from "./pages/board/BoardPage";
import { CookiesProvider } from "react-cookie";
import AdminPage from "./pages/admin/AdminPage";
import AuthPage from "./pages/auth/AuthPage";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <CookiesProvider>
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          <Route path="auth" element={<AuthPage/>}>
            <Route path="login" element={<LoginPage />}></Route>
            <Route path="register" element={<RegisterPage />}></Route>
          </Route>
          <Route path={""} element={<App />}>
            <Route path="/" element={<DomesticPage />}></Route>
            <Route path="anonymity" element={<AnonymityPage />}></Route>
            <Route path="board" element={<BoardPage />}></Route>
            <Route path="admin" element={<AdminPage />}></Route>
            <Route path="*" element={<NotFoundPage />}></Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </Provider>
  </CookiesProvider>
);
