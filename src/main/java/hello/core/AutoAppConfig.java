package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

// 6-1
@Configuration
// @Component가 붙은 클래스를 찾아 자동으로 스프링 빈에 등록해준다
@ComponentScan(
    // ComponentScan을 시작할 위치 지정(이 패키지를 포함하여 하위 패키지 탐색)
    // basePackages = "hello.core.member",
    // 제외할 스프링 빈 지정 (AppConfig)
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
