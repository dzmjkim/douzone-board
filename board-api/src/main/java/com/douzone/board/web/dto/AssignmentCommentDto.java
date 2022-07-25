package com.douzone.board.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssignmentCommentDto {

	private Long commentId;

	private String boardCategory;

	private String commentContent;

	private LocalDateTime commentCreateDt;

	private LocalDateTime commentModifyDt;

	private String username;

	private LocalDate assignmentDt;

	private Long parentCommentId;
}
