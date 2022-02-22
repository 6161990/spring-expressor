package com.todo2.dao;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="todov2")
public class TodoEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "deadline")
    private LocalDate deadlline;

    private boolean status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
