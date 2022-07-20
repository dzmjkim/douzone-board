package com.douzone.board.web.api;

import com.douzone.board.entity.User;
import com.douzone.board.service.LoginCheckService;
import com.douzone.board.service.UserService;
import com.douzone.board.web.dto.RegisterReqDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserApiController {
    private final UserService userService;
    private final LoginCheckService loginCheckService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterReqDto dto, BindingResult bindingResult)
        throws IllegalAccessException {
        log.info("회원가입을 시도합니다. username => " + dto.getUsername());

        if (bindingResult.hasErrors()) {
            throw new KeyAlreadyExistsException("회원가입에 실패 했습니다. username => " + dto.getUsername());
        }

        User user = dto.toEntity(); // dto -> entity
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return ResponseEntity.created(uri).body(userService.create(user));
    }

    @GetMapping("/api/token/refresh")
    public void refreshTokenCheck(HttpServletRequest request, HttpServletResponse response){
        loginCheckService.checkRefresh(request, response);
    }

    @GetMapping("/inputrefresh")
    @ResponseStatus(HttpStatus.OK)
    public void inputRefresh(HttpServletRequest request){
        loginCheckService.insertRefreshToken(request);
    }

}