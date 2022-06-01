package com.example.application.data.views;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {

    private String userId;
    private String userName;
    private String email;
    private String phoneNumber;
    private String isInactive;
    private String isBlock;
    private String signedAt;
    private String deletedAt;

    private Wallet wallet;
    private List<Membership> memberships;

    public int getTicketCount() {
        if(wallet.getTicketHistory() == null) {
            return 0;
        }
        return wallet.getTicketHistory().size();
    }

    public int getMembershipCount() {
        if(memberships == null) {
            return 0;
        }
        return memberships.size();
    }

}

/**
 * 유저의 정보에 비밀번호가 있어야할까? 휴면됨/block 의 정보가 유저에 있어야할까?
 * 없어도된다.
 * */
