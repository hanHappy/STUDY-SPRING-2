package hello.core.common;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

// 9-6 request 스코프 예제 만들기
// 로그를 출력하기 위한 클래스
@Component
// @Scope(value = "request")
// 9-8 스코프와 프록시
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log (String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }
    
    @PostConstruct
    public void init(){
        // HTTP request와 uuid를 연결시킨다
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean created : " + this);
    }
    
    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean closed : " + this);
    }
    
}
