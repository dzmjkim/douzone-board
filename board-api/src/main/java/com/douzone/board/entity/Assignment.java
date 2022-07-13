package com.douzone.board.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assignment {

    @Id
    @Column(name = "assignment_dt")
    private LocalDate assignmentDt;

    @Column(name = "assignment_title")
    private String assignmentTitle;

    @Column(name = "assignment_content")
    private String assignmentContent;

    @Column(name = "class_category")
    private Integer classCategory;

}
