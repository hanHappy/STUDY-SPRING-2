package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

// 9-6 request 스코프 예제 만들기
@Service
@RequiredArgsConstructor
public class LogDemoService {

    // 9-7 스코프와 Provider
    // private final ObjectProvider<MyLogger> myLoggerProvider;
    // 9-8 스코프와 프록시
    private final MyLogger myLogger;

    public void logic(String id) {
        // MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }

}
