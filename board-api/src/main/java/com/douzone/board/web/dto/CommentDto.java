package com.douzone.board.web.dto;

import com.douzone.board.entity.Assignment;
import com.douzone.board.entity.Board;
import com.douzone.board.entity.Comment;
import com.douzone.board.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {

    private Long commentNo;

    private String commentContent;

    private Long userId;

    private LocalDate assignment;

    private Long postNo;

    private Long topCommentNo;


}
