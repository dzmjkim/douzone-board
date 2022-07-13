package com.douzone.board.config.jwt;

public interface JwtProperties {
    String SECRET = "douzone"; // 우리 서버만 알고 있는 비밀값
    int ACCESS_TOKEN_EXPIRATION_TIME = 60000000; // 1분 (1/1000초)
    int REFRESH_TOKEN_EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
