package com.douzone.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserSessionService {
    void checkRefresh(HttpServletRequest request, HttpServletResponse response);

    void insertRefreshToken(HttpServletRequest request);


    void logout(String name);
}
