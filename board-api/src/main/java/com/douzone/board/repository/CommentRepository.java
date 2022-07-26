package com.douzone.board.repository;

import com.douzone.board.entity.Comment;
import com.douzone.board.web.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long>{
    List<Comment> findAllByBoardPostNo(Long postNo);
}
