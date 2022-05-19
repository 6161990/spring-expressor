package com.example.application.data.views;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Service {

    private String id;
    private String name;
    private String period;
    private String badge;

}
