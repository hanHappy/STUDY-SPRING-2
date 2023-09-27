package hello.core.singleton;

import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.text.Annotation;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

// 5-4
public class StatefulServiceTest {

    @Test
    void StatefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfit.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // TreadA : A 사용자 10,000 주문
        statefulService1.order("A", 10000);

        // TreadB : B 사용자 20,000 주문
        statefulService2.order("B", 20000);

        // ThreadA : 사용자 A 주문 금액 조회
        int price = statefulService1.getPrice();

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfit{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
    
}
