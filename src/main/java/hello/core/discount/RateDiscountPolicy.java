package hello.core.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;

@Component
// 7-7 애노테이션 직접 만들기
@MainDiscountPolicy
// 7-6
// @Primary
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP)
            return price * discountPercent / 100;
        else
            return 0;
    }
    
}
