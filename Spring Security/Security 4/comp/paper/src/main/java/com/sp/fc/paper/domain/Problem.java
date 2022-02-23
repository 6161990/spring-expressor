package com.sp.fc.paper.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sp_problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problemId;  //문제 아이디

    private Long paperTemplateId; //시험 템플릿 아이디

    private int indexNum; //1 based(1부터 시작)

    private String content;

    private String answer; //PaperAnswer와 일치하다면 correct

    @Column(updatable = false)
    private LocalDateTime created;

    private LocalDateTime updated;
}
