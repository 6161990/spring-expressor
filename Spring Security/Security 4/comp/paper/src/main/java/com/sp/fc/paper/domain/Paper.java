package com.sp.fc.paper.domain;


import com.sp.fc.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="sp_paper")
public class Paper {

    /* 학생들이 보는 시험지
    * PaperTemplate을 복사함. -> template으로 부터 paper가 만들어짐
    * studyUserId (학생 아이디)와 함께 배부됨.
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paperId;

    private Long paperTemplateId;
    private String name; //paperTemplate의 이름을 그대로 받는 papername

    private Long studyUserId;  //학생 아이디

    @Transient //보통 DB에서 다시 fetch 해올 일이 없는 필드에 사용
    private User user;  //@Transient하게 꽂아넣을 수 있도록 준비(실제 Paper table에는 생성되지 않는 필드)

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "paper")
    private List<PaperAnswer> paperAnswerList;

    private LocalDateTime created;
    private LocalDateTime startTime; //시험 시작 시간
    private LocalDateTime endTime;  //시험 종료 시간

    private PaperState state;

    public static enum PaperState {
        READY,  //Paper가 created 되었을 때
        START,  //Paper가 시작되었을 때
        END,    //Paper가 종료 되었을 때(채점 버튼을 눌렀을 때 end) 상태로
        CANCELED
    }

    private int total;
    private int answered;
    private int correct;

    @Transient
    public double getScore(){
        if(total < 1) return 0;
        return correct* 100.0 / total;
    }

    public void addAnswered() {
        answered++;
    }

    public void addCorrect() {
        correct++;
    }

    public Map<Integer, PaperAnswer> answerMap(){
        if(paperAnswerList == null) return new HashMap<>();
        return paperAnswerList.stream().collect(Collectors.toMap(PaperAnswer::num,
                Function.identity()));
    }

}