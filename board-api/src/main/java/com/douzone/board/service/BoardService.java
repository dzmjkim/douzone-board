package com.douzone.board.service;

import com.douzone.board.entity.Board;
import com.douzone.board.repository.BoardRepository;
import com.douzone.board.repository.UserRepository;
import com.douzone.board.web.dto.PostCreateReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void create(PostCreateReqDto dto, String username) {
        boardRepository.save(Board.builder()
                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .postCreateDt(LocalDateTime.now())
                .user(userRepository.findByUsername(username))
                .build());
        log.info("{} 님이 게시물을 작성하였습니다.", username);
    }
}