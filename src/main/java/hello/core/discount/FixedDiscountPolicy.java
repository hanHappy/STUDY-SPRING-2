package hello.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;
@Component
public class FixedDiscountPolicy implements DiscountPolicy {

    private int discountFixedAmount = 1000; // 1,000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP)
            return discountFixedAmount;
        else
            return 0;
    }

}
