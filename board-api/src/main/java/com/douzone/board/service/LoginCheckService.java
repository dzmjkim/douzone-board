package com.douzone.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

public interface LoginCheckService {
    void checkRefresh(HttpServletRequest request, HttpServletResponse response);

    void insertRefreshToken(HttpServletRequest request);
}
