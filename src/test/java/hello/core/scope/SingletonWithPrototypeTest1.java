package hello.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;

// 9-3
public class SingletonWithPrototypeTest1 {
    
    @Test
    @DisplayName("아래 테스트와 비교용/ 서로 다른 프로토타입빈에서 addCount를 한다면 각각의 결과값은 1이다")
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

        ac.close();
    }

    @Test
    @DisplayName("서로 다른 클라이언트가 주입된 동일한 프로토타입 빈을 사용한다면 count는 누적된다")
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);
        
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

    }

    // default가 singleton이기 때문에 작성하지 않아도 되나 이해를 돕기 위해 작성함
    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
        // private final PrototypeBean prototypeBean;

        // 9-4 프로토타입 스코프 - 싱글톤 빈과 함께 사용 시 Provider로 문제 해결
        @Autowired
        // java 표준인 JSR-330의 Provider
        private Provider<PrototypeBean> prototypeBeanProvider;
        // Spring framework가 제공하는 Provider
        // private ObjectProvider<PrototypeBean> prototypeBeanProvider;
    
        public int logic() {
            // 9-4
            // getObject()를 호출하면 스프링 컨테이너에서 prototypeBean을 찾아 반환해준다
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }

    }


}
