package com.example.swagger.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {

    @ApiModelProperty(value = "사용자의 이름", example = "jenny", required = true)
    private String name;
    
    @ApiModelProperty(value = "사용자의 나이", example = "21", required = true)
    private int age;
}
