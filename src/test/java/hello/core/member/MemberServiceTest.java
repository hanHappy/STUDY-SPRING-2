package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService = new MemberServiceImp();

    @Test
    void join(){
        // given
        Member member = new Member(1L, "A", Grade.VIP);

        // when
        memberService.join(member);
        Member foundMember = memberService.findMember(1L);

        // then
        // org.assertj.core.api.Assertions
        Assertions.assertThat(member).isEqualTo(foundMember);
    }
}
