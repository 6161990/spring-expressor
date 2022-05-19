package com.example.application.data.views;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {

    private Service service;
    private MembershipInfo membershipInfo;
    private String reason;
    private String state;
    private String createdAt;
    private String terminatedAt;
    private String expiredAt;


}
