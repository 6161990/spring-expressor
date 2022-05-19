package com.example.application.data.views;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private String id;
    private String name;
    private String phoneNumber;
    private String joinedAt;
    private String isInactive;
    private String isBlock;
    private String deletedAt;

    private List<Ticket> tickets;
    private List<Membership> memberships;

}
