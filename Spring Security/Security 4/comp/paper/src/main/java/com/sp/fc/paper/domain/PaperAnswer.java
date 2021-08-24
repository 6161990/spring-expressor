package com.sp.fc.paper.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sp_paper_answer")
public class PaperAnswer {

    /* 학생들은 자신에게 부여된 Paper(시험지)를 받아서
    * 각 paperId에 각 번호 (num) 대로 답(answer)을 달아서
    * 나중에 채점(correct)을 했을 때 score을 받게됨
    * */

    @JsonIgnore
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "paperId"))
    Paper paper;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class PaperAnswerId implements Serializable{
        private Long paperId; //해당 PaperAnswer는 어떤 시험지PaperId의 답이냐
        private Integer num; // 1base , 그 시험지(PaperId)의 몇번째 답이냐
    }

    @EmbeddedId
    private PaperAnswerId id;

    public Integer num(){
        return id.getNum();
    }

    private Long problemId;

    private String answer;

    private boolean correct;

    private LocalDateTime answered; //updatable
}
