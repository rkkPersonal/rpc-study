package org.kk.study;

import org.kk.rpc.spring.annotation.KkEnableRpc;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Hello world!
 */
@ComponentScan("org.kk")
@KkEnableRpc
@PropertySource(value = "classpath:/kkrpc-property.properties")
public class OrderApp {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(OrderApp.class);
        annotationConfigApplicationContext.start();
        System.out.println("OrderApp Hello World Start Up!");
        System.in.read();
    }
}
