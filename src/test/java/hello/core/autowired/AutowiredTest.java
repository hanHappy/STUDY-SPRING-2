package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import hello.core.member.Member;

// 7-2
public class AutowiredTest {
    
    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // required = false를 작성하지 않으면 기본 옵션이 true기 때문에 UnsatisfiedDependencyException 발생
        // 의존 관계가 없을 경우 아래 method가 호출되지 않는다
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){ // 호출 X
            System.out.println("noBean1 : " + noBean1); // 
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 : " + noBean2); // noBean2 + null
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 : " + noBean3); // noBean3 + Optional.empty
        }
    }
}
