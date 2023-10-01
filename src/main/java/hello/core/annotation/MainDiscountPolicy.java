package hello.core.annotation;

import java.lang.annotation.*;

import org.springframework.beans.factory.annotation.Qualifier;

// 7-7 애노테이션 직접 만들기
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {

}
