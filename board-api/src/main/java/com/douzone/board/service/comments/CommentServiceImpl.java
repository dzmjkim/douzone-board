package com.douzone.board.service.comments;

import com.douzone.board.entity.Comment;
import com.douzone.board.repository.BoardRepository;
import com.douzone.board.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.douzone.board.repository.UserRepository;
import com.douzone.board.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	@Override
	public List<CommentDto> findAll(Long postNo) {
		List<CommentDto> commentList = new ArrayList<>();
		List<Comment> comments = commentRepository.findAllByBoardPostNo(postNo);

		comments.forEach(comment ->
				commentList.add(CommentDto.builder()
						.commentContent(comment.getCommentContent())
						.commentId(comment.getCommentId())
						.userId(comment.getUser().getId())
						.topCommentNo(comment.getComment().getCommentId())
						.postNo(comment.getBoard().getPostNo())
						.build()));

		return commentList;
	}

	@Override
	public void createComment(CommentDto commentDto) {
		commentRepository.save(Comment.builder()
				.commentContent(commentDto.getCommentContent())
				.board(boardRepository.findById(commentDto.getPostNo()).orElseThrow(IllegalArgumentException::new))
				.commentCreateDt(LocalDateTime.now())
				.user(userRepository.findByUsername(commentDto.getuser)).build());
	}
}
