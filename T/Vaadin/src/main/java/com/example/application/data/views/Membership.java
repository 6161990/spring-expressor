package com.example.application.data.views;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Membership {

    private MembershipInfo membershipInfo;
    private String periodOfMembership;
    private String state;
    private List<ServiceRunningContext> serviceRunningContexts;

}
