package org.kk.rpc.spring.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author Steven
 * <p>
 * remark service is a provider
 */
@Service
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KkRpcService {


    /**
     * 如果有多个接口，需要自己指定一个
     */
    Class<?> interfaceClass() default void.class;
}
