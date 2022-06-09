package com.example.application.data.views;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceRunningContext {

    private String serviceName;
    private String impossibleTerminatedAt;
    private String state;
    private String createdAt;
    private String expiredAt;
    private String terminatedAt;
    private String servicePeriod;
}
