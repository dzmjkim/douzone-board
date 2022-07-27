package com.douzone.board.service.comments;

import com.douzone.board.web.dto.CommentDto;

import java.util.List;

public interface CommentService {
	List<CommentDto> findAll(Long postNo);

    void createComment(CommentDto commentDto);


    void modifyComment(CommentDto commentDto);

    void removeComment(Long commentId);
}
