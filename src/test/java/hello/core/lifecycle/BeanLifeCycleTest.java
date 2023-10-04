package hello.core.lifecycle;

import static org.mockito.ArgumentMatchers.refEq;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 8-1 빈 생명주기 콜백 시작
public class BeanLifeCycleTest {
    
    // @Test
    public void lifeCycleTest(){
        // ApplicationContext 레벨에서 지원하지 않는 close()를 사용하기 위한 더 깊은 레벨의 인터페이스
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    // @Configuration
    static class LifeCycleConfig {
        // 8-3 외부라이브러리를 초기화, 종료해야 한다면 아래 방법을 사용하자
        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
