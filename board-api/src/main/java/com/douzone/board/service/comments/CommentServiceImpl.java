package com.douzone.board.service.comments;

import com.douzone.board.web.dto.AssignmentCommentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final
	@Override
	public List<AssignmentCommentDto> findAll() {

		return null;
	}
}
