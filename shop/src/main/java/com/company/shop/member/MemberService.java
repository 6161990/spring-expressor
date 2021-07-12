package com.company.shop.member;

public interface MemberService {

    void join(Member memer);

    Member findMember(Long memberId);
}
