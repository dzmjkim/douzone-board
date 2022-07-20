package com.douzone.board.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.AfterDomainEventPublication;

@Data
@AllArgsConstructor
public class UserDto {
    private String username;
    private String name;
    private Integer userClass;
}
