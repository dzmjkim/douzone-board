package com.douzone.board.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Anonymity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_id")
    private Long id;

    @Column(name = "mail_create_dt")
    private LocalDateTime mailCreateDt;

    @Column(name = "send_yn")
    private String sendYn;

    @Column(name = "mail_content")
    private String mailContent;
}
