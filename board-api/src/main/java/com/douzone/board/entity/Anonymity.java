package com.douzone.board.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name = "mail_create_dt")
    private LocalDateTime mailCreateDt;

    @Column(name = "send_yn")
    private String sendYn;

    @Column(name = "mail_content")
    private String mailContent;

}
