package com.todo2.dao;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class TodoForm {

    private long Id;

    @NotNull
    @Size(min = 1, max = 30)
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    private boolean status;
}
