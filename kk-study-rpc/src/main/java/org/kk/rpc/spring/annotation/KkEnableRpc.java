package org.kk.rpc.spring.annotation;

import org.kk.rpc.spring.KkConfiguration;
import org.kk.rpc.spring.KkPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Steven
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {KkPostProcessor.class, KkConfiguration.class})
public @interface KkEnableRpc {
}
