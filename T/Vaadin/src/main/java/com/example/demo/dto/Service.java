package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Service {

    private String id;
    private String name;
    private String period;
}
