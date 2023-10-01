package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImp;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImp;

// 앱의 설정 정보를 담당하는 클래스
@Configuration
public class AppConfig {

    // Spring Container에 등록된다
    @Bean
    public MemberService memberService() {
        return new MemberServiceImp(memberRepository());
    }

    // 생성자 주입
    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImp(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixedDiscountPolicy();
    }
}
