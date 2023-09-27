package hello.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

// 4-4
// 스프링 빈 조회 - 동일한 타입이 둘 이상
public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
    
    @Configuration
    static class SameBeanConfig { // 이 테스트 클래스 내부에서만 사용하는 static class
        @Bean()
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }
    
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
    
    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다")
    void findBeanByTypeDuplicate() {

        // NoUniqueBeanDefinitionException 발생
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("같은 타입이 둘 이상 있을 때 빈 이름을 지정한다")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfTypes = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfTypes.keySet()) {
            System.out.println("Key : " + key + " value : " + beansOfTypes.get(key));
        }
        System.out.println("beansOfType : " + beansOfTypes);
        assertThat(beansOfTypes.size()).isEqualTo(2);
    }

}
