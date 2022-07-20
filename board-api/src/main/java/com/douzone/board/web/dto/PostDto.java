package com.douzone.board.web.dto;

import com.douzone.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PostDto {
    private Long postNo;
    private String postTitle;
    private String postContent;
    private LocalDateTime postCreateDt;
    private LocalDateTime postModifyDt;
    private UserDto userDto;
}
