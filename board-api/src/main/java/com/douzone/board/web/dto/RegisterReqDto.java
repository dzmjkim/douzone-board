package com.douzone.board.web.dto;

import com.douzone.board.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterReqDto {
    // https://bamdule.tistory.com/35 (@Valid 어노테이션 종류)
    @Size(min = 2, max = 20)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private int userClass;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .userClass(userClass)
                .build();
    }
}
