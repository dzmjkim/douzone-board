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
public class Board {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_create_dt")
    private LocalDateTime postCreateDt;

    @Column(name = "post_modify_dt")
    private LocalDateTime postModifyDt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
