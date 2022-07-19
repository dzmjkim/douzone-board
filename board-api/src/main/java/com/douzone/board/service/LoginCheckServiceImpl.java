package com.douzone.board.service;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.douzone.board.config.jwt.JwtProperties;
import com.douzone.board.entity.User;
import com.douzone.board.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginCheckServiceImpl implements LoginCheckService{

    private final UserService userService;
    private final UserRepository userRepository;


    @Override
    public void checkrefresh(HttpServletRequest request,
                             HttpServletResponse response) {

        // 사용자가 토큰을 갱신할 수 있도록 요청을 설정할 수 있는 다른 끝점을 만듭니다.
        // (refresh token 을 Client 가 보내면 그것을 받아서 만료기간을 확인 후 다른 access token 을 부여할 것입니다.)
            // 인증 헤더 찾기
            // Client 가 refresh token 을 보낼때 Bearer 과 함께 보낼 거임.
            String authorizationHeader = request.getHeader(AUTHORIZATION);

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    // token 검증 작업
                    String refreshToken = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256(JwtProperties.SECRET.getBytes()); // 토큰 생성할 때와 같은 알고리즘으로 풀어야함.
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(refreshToken);

                    // 토큰이 유효한지 확인되면, 사용자의 이름을 가져올 수 있습니다.
                    String username = decodedJWT.getSubject(); // token 과 함께 제공되는 사용자 이름을 줍니다.
                    UserDetails user = userService.loadUserByUsername(username);
                    String refreshTokenInDatabase = userRepository.findRefreshTokenByUsername(username);

                    if(!Objects.equals(refreshToken, refreshTokenInDatabase)){
                        throw new IllegalAccessException();
                    }

                    String access_token = JWT.create()
                                             .withSubject(user.getUsername())
                                             .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
                                             .withIssuer(request.getRequestURL().toString())
                                             .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                                                 Collectors.toList()))
                                             .sign(algorithm);

                    /* token body 로 던지기 */
                    Map<String, String> tokens = new HashMap<>();
                    tokens.put("access_token", access_token);
                    tokens.put("refresh_token", refreshToken);

                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                } catch (Exception exception) {
                    // exception 1 : token 이 유효하지 않을 때 (token 을 확인할 수 없거나, 유효기간이 지났을 경우)
                    response.setHeader("error", exception.getMessage());

                    /* error 를 401 으로 던지기 (둘중 하나만 할 수 있음) */
//                    response.sendError(FORBIDDEN.value()); // 권한이 없기 때문에 403 던져버리기~!

                    /* error 를 body 로 던지기 (둘중 하나만 할 수 있음) */
                    response.setStatus(UNAUTHORIZED.value());
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    // new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                throw new RuntimeException("Refresh token is missing");
            }
        }

    }
// }
