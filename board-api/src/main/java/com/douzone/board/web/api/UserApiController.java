package com.douzone.board.web.api;

import com.douzone.board.entity.User;
import com.douzone.board.service.UserService;
import com.douzone.board.web.dto.LoginReqDto;
import com.douzone.board.web.dto.RegisterReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserApiController {
    private final UserService userService;

    // 회원가입
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterReqDto dto, BindingResult bindingResult) {
        log.info("회원가입을 시도합니다. username => " + dto.getUsername());

        if (bindingResult.hasErrors()) {
            throw new KeyAlreadyExistsException("회원가입에 실패 했습니다. username => " + dto.getUsername());
        }

        User user = dto.toEntity(); // dto -> entity
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return ResponseEntity.created(uri).body(userService.create(user));
    }

}