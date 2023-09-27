package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImp implements OrderService{

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImp(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 설계가 잘 된 예
        // order service에서는 할인에 대해 몰라도 된다
        // 할인 정책에 수정이 필요하다면 order는 두고 할인 코드를 고치면 되는 것
        // 단일 책임 원칙을 잘 지킨 것
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 5-5 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
