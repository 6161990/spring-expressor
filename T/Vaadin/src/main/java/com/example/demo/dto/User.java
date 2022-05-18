package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {

    private String id;
    private String name;
    private String email;

    private String coupon;
    private List<Membership> memberships;

    public boolean hasMembership(String membershipId) {
        return memberships.stream().anyMatch(membership -> membership.getId().equals(membershipId));
    }
}
