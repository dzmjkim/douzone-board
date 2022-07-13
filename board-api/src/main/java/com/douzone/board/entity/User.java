package com.douzone.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    private String name;

    private String password;

    @Column(name = "class")
    private Integer userClass;

}