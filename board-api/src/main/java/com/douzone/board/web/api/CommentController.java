package com.douzone.board.web.api;

import com.douzone.board.service.comments.CommentService;
import com.douzone.board.web.dto.AssignmentCommentDto;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/getAll/{assignmentDt}")
	public List<AssignmentCommentDto> getAllComments(@PathVariable LocalDate assignmentDt){
		return commentService.findAll();
	}
}
