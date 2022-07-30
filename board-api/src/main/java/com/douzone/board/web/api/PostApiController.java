package com.douzone.board.web.api;

import com.douzone.board.service.PostService;
import com.douzone.board.web.dto.PostCreateReqDto;
import com.douzone.board.web.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    /**
     * 게시글 조회
     */
    @GetMapping("/board")
    public ResponseEntity<List<PostDto>> getAll() {
        return ResponseEntity.ok().body(postService.findAll());
    }

    /**
     * 게시글 작성
     */
    @PostMapping("/board")
    public ResponseEntity<?> create(@RequestBody PostCreateReqDto dto, Principal principal) {
        if (Objects.isNull(dto.getPostContent()) || Objects.isNull(dto.getPostTitle())) {
            log.error("게시글 제목 또는 내용이 비어있습니다. 제목 => {} 내용 => {}", dto.getPostTitle(), dto.getPostContent());
            return ResponseEntity.badRequest().build();
        }
        postService.create(dto, principal.getName());

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/board").toUriString());
        return ResponseEntity.created(uri).build();
    }

    /**
     * 단일 게시글 조회
     */
    @GetMapping("/board/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok().body(postService.findById(id));
    }
}