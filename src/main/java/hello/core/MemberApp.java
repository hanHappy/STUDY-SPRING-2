package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImp;

// test 용도로 잠시 사용하고 jUnit으로 넘어감
public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImp();
        Member member = new Member(1L, "A", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("found member = " + foundMember.getName());

        System.out.println(member == foundMember);
    }
}
