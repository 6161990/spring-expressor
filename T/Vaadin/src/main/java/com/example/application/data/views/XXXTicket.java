package com.example.application.data.views;

import lombok.Data;

import java.util.List;

public class XXXTicket extends Ticket {

    private String numberOfAvailableTickets;

    private List<String> usedAts;

    XXXTicket(Service service, MembershipInfo membershipInfo, String reason, String state, String createdAt, String terminatedAt, String expiredAt) {
        super(service, membershipInfo, reason, state, createdAt, terminatedAt, expiredAt);
    }
}
