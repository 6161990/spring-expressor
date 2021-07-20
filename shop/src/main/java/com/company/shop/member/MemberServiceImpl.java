package com.company.shop.member;

public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //추상화에도 의존, 구체화에도 의존하고 있음 DIP의 위반

    private final MemberRepository memberRepository;
    // => AppConfig를 통해 생성자 주입 추상화(인터페이스)에만 의존하게됨

    //AppConfig 를 통해서 구현당함
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
