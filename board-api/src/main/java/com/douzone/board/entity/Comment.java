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
public class Comment {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "board_category")
    private String boardCategory;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_create_dt")
    private LocalDateTime commentCreateDt;

    @Column(name = "comment_modify_dt")
    private LocalDateTime commentModifyDt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "assignment_dt")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "top_comment_id")
    private Comment comment;


}
