package com.douzone.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginCheckService {
    void checkrefresh(HttpServletRequest request, HttpServletResponse response);
}
