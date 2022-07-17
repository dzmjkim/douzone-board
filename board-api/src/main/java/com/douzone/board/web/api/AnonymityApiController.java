package com.douzone.board.web.api;

import com.douzone.board.service.AnonymityService;
import com.douzone.board.web.dto.AnonymityDto;
import com.douzone.board.web.dto.AnonymityReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AnonymityApiController {
    private final AnonymityService anonymityService;

    @GetMapping("/anonymity")
    public ResponseEntity<List<AnonymityDto>> getAll() {
        return ResponseEntity.ok().body(anonymityService.findAll());
    }

    @PostMapping("/anonymity")
    public ResponseEntity<?> create(@RequestBody AnonymityReqDto dto) {
        anonymityService.create(dto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/anonymity").toUriString());
        return ResponseEntity.created(uri).build();
    }
}