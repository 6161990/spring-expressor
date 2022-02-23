package com.sp.fc.paper.domain;

import com.sp.fc.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="sp_paper_template")
public class PaperTemplate {
    /* 선생님이 작성하는 시험지 , 문제를 하나씩 추가하도록 되어있음
    * so, Problem에는 paperTemplateId를 가지고 있음
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paperTemplateId;  //고유번호

    private String name;  //시험지 이름

    private Long userId;  //작성자 아이디

    @Transient
    private User creator;
    // 필요한 경우에 꽂아줘야하기 때문에 userService의 userId 리스트를 넘겼을 때 id로 user를 테이블로 가지고 있는
    // (임시테이블로 가지고 있는) map 객체를 반환하는 메소드가 아주 유용하게 쓰임


    private int total; //문제의 전체 갯수

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(foreignKey = @ForeignKey(name="paperTemplateId"))
    private List<Problem> problemList;

    private long publishedCount;  //배부된 시험지의 갯수

    private long completeCount;  //학생이 마친 시험 갯수

    @Column(updatable = false)
    private LocalDateTime created;

    private LocalDateTime updated;

}