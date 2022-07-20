package com.douzone.board.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String boardCategory;

    @Column(name = "comment_content")
    @NotNull
    private String commentContent;

    @Column(name = "comment_create_dt")
    @NotNull
    private LocalDateTime commentCreateDt;

    @Column(name = "comment_modify_dt")
    private LocalDateTime commentModifyDt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "assignment_dt")
    @NotNull
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "top_comment_id")
    @NotNull
    private Comment comment;


}
