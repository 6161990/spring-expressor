package com.kh.test01.application.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BoardForm {
    private String name;
    private String email;
    private String content;

}
