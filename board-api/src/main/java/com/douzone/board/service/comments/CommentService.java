package com.douzone.board.service.comments;

import com.douzone.board.web.dto.AssignmentCommentDto;
import java.util.List;

public interface CommentService {
	List<AssignmentCommentDto> findAll();
}
