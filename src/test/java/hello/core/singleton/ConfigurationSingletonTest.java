package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImp;
import hello.core.order.OrderServiceImp;

// 5-5
public class ConfigurationSingletonTest {
    
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberServiceImp memberService = ac.getBean("memberService", MemberServiceImp.class);
        OrderServiceImp orderService = ac.getBean("orderService", OrderServiceImp.class);
        
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        
        // 전부 같은 객체를 참조하는 모습
        System.out.println("memberService -> memberRepository : " + memberRepository1); // @7d9e8ef7
        System.out.println("orderService -> memberRepository : " + memberRepository2);  // @7d9e8ef7
        System.out.println("memberRepository : " + memberRepository);                   // @7d9e8ef7

        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }
    
    @Test
    void configutationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean : " + bean.getClass());    // class hello.core.AppConfig$$SpringCGLIB$$0
    }
}
