package com.company.shop.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
