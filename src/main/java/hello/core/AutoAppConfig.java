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
    // 처음 스프링이 로드될 때 MemoryMemberRepository가 memoryMemberRepository라는 이름으로
    // 빈에 등록되는데 아래와 같이 동일한 이름으로 빈을 수동 등록할 경우
    // 자동 <-> 수동 빈이름 충돌이 발생한다
    // @Bean(name = "memoryMemberRepository")
    // MemberRepository memberRepository(){
    //     return new MemoryMemberRepository();
    // }
}
