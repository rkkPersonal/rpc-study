package org.kk.rpc.spring.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author Steven
 */
@Service
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KkRpcReference {


    String loadBalance() default "randomLoadBalance";
}
