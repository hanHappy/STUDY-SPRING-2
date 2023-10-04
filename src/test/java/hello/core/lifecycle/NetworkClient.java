package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

// 8-1 빈 생명주기 콜백 시작
public class NetworkClient{
    
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call: " + url + " / message: " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect(){
        System.out.println("close " + url);
    }

    // 8-2
    // 의존관계 주입이 끝나면 호출
    // 8-4 애노테이션
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet()");
        connect();
        call("초기화 연결 메시지");
    }

    // 스프링이 종료되기 직전 호출
    // 8-4 애노테이션
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.destroy()");
        disconnect();
    }
    
}
