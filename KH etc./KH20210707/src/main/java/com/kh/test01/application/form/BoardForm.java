package com.kh.test01.application.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BoardForm {
    @Nullable
    @Length(max=20)
    private String name;

    @Nullable
    @Email
    @Length(max = 100)
    private String email;

    @NotNull
    @Length(min = 1, max=400)
    private String content;

}
