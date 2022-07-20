package com.douzone.board.service;

import com.douzone.board.entity.Anonymity;
import com.douzone.board.repository.AnonymityRepository;
import com.douzone.board.web.dto.AnonymityDto;
import com.douzone.board.web.dto.AnonymityReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AnonymityService {
    private final AnonymityRepository anonymityRepository;

    public List<AnonymityDto> findAll() {
        log.info("익명 소리함을 열람합니다.");
        List<AnonymityDto> result = new ArrayList<>();

        for (Anonymity anonymity : anonymityRepository.findAll()) {
            result.add(AnonymityDto.builder()
                    .id(anonymity.getId())
                    .mailCreateDt(anonymity.getMailCreateDt())
                    .mailContent(anonymity.getMailContent())
                    .sendYn(anonymity.getSendYn())
                    .build());
        }

        return result;
    }

    // init 용
    public void saveAnonymity(Anonymity anonymity) {
        anonymityRepository.save(anonymity);
    }

    public void create(AnonymityReqDto reqDto) {
        log.info("누군가 마음의 편지를 작성하였습니다.");

        anonymityRepository.save(Anonymity.builder()
                .mailCreateDt(LocalDateTime.now())
                .mailContent(reqDto.getMailContent())
                .sendYn("N")
                .build());
    }
}