package hello.core.beanfind;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImp;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// 4-3 스프링 빈 조회 - 기본
public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("타입으로만 조회")
    void findBeanByType(){
        // 스프링 빈에 등록된 인스턴스의 타입에 의해 결정되기 때문에 인터페이스(MemberService)로 조회가 가능하다
        // AppConfig에서 memberService()가 MemberServiceImp를 반환하고 있음
        MemberService memberService = ac.getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByType2(){
        MemberService memberService = ac.getBean(MemberServiceImp.class);

        assertThat(memberService).isInstanceOf(MemberServiceImp.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByName_X(){

        assertThrows(NoSuchBeanDefinitionException.class, 
            () -> ac.getBean("exeception test", MemberService.class));
    }
}
