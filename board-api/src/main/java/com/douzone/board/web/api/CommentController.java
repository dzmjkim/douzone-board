package com.douzone.board.web.api;

import com.douzone.board.service.comments.CommentService;
import com.douzone.board.web.dto.AssignmentCommentDto;
import java.time.LocalDate;
import java.util.List;

import com.douzone.board.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/getAllByPost/{postNo}")
	public List<CommentDto> getAllCommentsByPostNo(@PathVariable Long postNo){
		return commentService.findAll(postNo);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public void newComment(@RequestBody CommentDto commentDto){
		commentService.createComment(commentDto);
	}

	@PutMapping("/modify")
	@ResponseStatus(HttpStatus.OK)
	public void editComment(@RequestBody CommentDto commentDto){ commentService.modifyComment(commentDto); }

	@DeleteMapping("/remove/{commentId}")
	@ResponseStatus(HttpStatus.OK)
	public void eraseComment(@PathVariable Long commentId, HttpServletRequest request, HttpServletResponse response){
		commentService.removeComment(commentId, request, response); };
	
}
