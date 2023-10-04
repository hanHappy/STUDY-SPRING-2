package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

// 9-6 request 스코프 예제 만들기
@Controller
@RequiredArgsConstructor
public class LogDemoController {
    
    private final LogDemoService logDemoService;
    // 9-7 스코프와 Provider
    // private final ObjectProvider<MyLogger> myLoggerProvider;
    // 9-8 스코프와 프록시
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        
        System.out.println(myLogger.getClass());

        // MyLogger가 만들어지는 최초 시점
        // MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
    
}
