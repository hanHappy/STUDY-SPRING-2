package hello.core.member;

public class MemberServiceImp implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImp(MemberRepository memberRepository) {
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

    // 5-5 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
    
}
