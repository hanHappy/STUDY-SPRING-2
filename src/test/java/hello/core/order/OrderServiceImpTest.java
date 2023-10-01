package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.discount.FixedDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpTest {
    
    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImp orderService = new OrderServiceImp(memberRepository, new FixedDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
