package com.douzone.board.service.comments;

import com.auth0.jwt.JWT;
import com.douzone.board.entity.Comment;
import com.douzone.board.repository.BoardRepository;
import com.douzone.board.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.douzone.board.repository.UserRepository;
import com.douzone.board.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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
						.commentNo(comment.getCommentNo())
						.userId(comment.getUser().getId())
						.username(comment.getUser().getUsername())
						.topCommentNo(comment.getTopCommentNo())
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
				.user(userRepository.findById(commentDto.getUserId()).orElseThrow(IllegalArgumentException::new))
				.topCommentNo(commentDto.getTopCommentNo()).build());
	}

	@Override
	public void modifyComment(CommentDto commentDto) {

		Comment comment = commentRepository.findById(commentDto.getCommentNo()).orElseThrow(NullPointerException::new);
		comment.setCommentContent(commentDto.getCommentContent());
		comment.setCommentModifyDt(LocalDateTime.now());

		commentRepository.save(comment);
	}

	@Override
	public void removeComment(Long commentId, HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(AUTHORIZATION);
		String encodedToken = token.split("Bearer ")[1];
		String username = JWT.decode(encodedToken).getSubject();

		Comment comment = commentRepository.findById(commentId).orElseThrow(IllegalAccessError::new);

		if(!Objects.equals(username, comment.getUser().getUsername())){
			response.setStatus(400);
		}else {
			commentRepository.deleteById(commentId);
		}
	}
}
