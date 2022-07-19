package com.douzone.board.service;

import com.douzone.board.entity.Anonymity;
import com.douzone.board.entity.Board;
import com.douzone.board.entity.User;
import com.douzone.board.repository.BoardRepository;
import com.douzone.board.repository.UserRepository;
import com.douzone.board.web.dto.AnonymityDto;
import com.douzone.board.web.dto.PostCreateReqDto;
import com.douzone.board.web.dto.PostDto;
import com.douzone.board.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public List<PostDto> findAll() {
        List<PostDto> result = new ArrayList<>();

        log.info("게시글 목록을 조회합니다.");
        for (Board board : boardRepository.findAll()) {
            User user = board.getUser();

            result.add(PostDto.builder()
                            .postNo(board.getPostNo())
                            .postTitle(board.getPostTitle())
                            .postContent(board.getPostContent())
                            .postCreateDt(board.getPostCreateDt())
                            .postModifyDt(board.getPostModifyDt())
                            .userDto(new UserDto(user.getUsername(), user.getName(), user.getUserClass()))
                    .build());
        }

        return result;
    }

    public void create(PostCreateReqDto dto, String username) {
        log.info("{} 님이 게시물을 작성하였습니다.", username);

        boardRepository.save(Board.builder()
                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .postCreateDt(LocalDateTime.now())
                .user(userRepository.findByUsername(username))
                .build());
    }
}