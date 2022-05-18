package com.example.demo.dto;


import com.example.demo.dto.Service;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Membership {

    private String id;
    private String name;
    private String userId;
    private List<Service> services;
}
