package hello.core.scope;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

// 9-2
public class PrototypeTest {

    @Test
    void prototypeBeanFine() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 : " + prototypeBean1);
        System.out.println("prototypeBean2 : " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        // 필요하다면 수동으로 destroy해줘야 한다
        // prototypeBean1.destroy();
        // prototypeBean2.destroy();

        // Spring Container가 종료되어도 destroy()가 호출되지 않는다
        ac.close();
    }
    
    // AnnotationConfigApplicationContext에 PrototypeBean.class를 등록하면 PrototypeBean을 컴포넌트 스캔 대상에 포함하여 빈으로 등록된다
    // 그래서 @Component 붙여주지 않아도 된다
    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonTest.singletonBean.init()");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonTest.singletonBean.destroy()");
        }
    }
}
