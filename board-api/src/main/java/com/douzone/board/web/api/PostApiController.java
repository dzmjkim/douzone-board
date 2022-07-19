package com.douzone.board.web.api;

import com.douzone.board.service.BoardService;
import com.douzone.board.web.dto.PostCreateReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final BoardService boardService;

//    @GetMapping("/board")
//    public ResponseEntity<List<AnonymityDto>> getAll() {
//        return ResponseEntity.ok().body(boardService.findAll());
//    }

    /**
     * 게시글 작성
     */
    @PostMapping("/board")
    public ResponseEntity<?> create(@RequestBody PostCreateReqDto dto, Principal principal) {
        boardService.create(dto, principal.getName());

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/board").toUriString());
        return ResponseEntity.created(uri).build();
    }
}