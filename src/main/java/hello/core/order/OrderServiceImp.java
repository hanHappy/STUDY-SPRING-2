package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
// 7-4 롬복과 최신 트렌드
// final이 붙은 것을 가진 것에 대해 생성자를 만들어준다
// @RequiredArgsConstructor
public class OrderServiceImp implements OrderService{

    // final - 무조건 값이 있어야 한다
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 7-1 생성자 주입
    // 가장 많이 사용하는 방법
    // 스프링 컨테이너에서 스프링 빈을 꺼내 이곳에 주입해준다
    // 특별한 경우가 아니라면 7-4에서 사용한 것처럼 @RequiredArgsContructor를 사용한다
    @Autowired
    public OrderServiceImp(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
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
