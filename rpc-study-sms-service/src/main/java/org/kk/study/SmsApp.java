package org.kk.study;

import org.kk.rpc.spring.annotation.KkEnableRpc;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 * Hello world!
 */
@ComponentScan("org.kk.study")
@KkEnableRpc
@PropertySource(value = "classpath:/kkrpc-property.properties")
public class SmsApp {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(SmsApp.class);
        annotationConfigApplicationContext.start();
        System.out.println("SmsApp Hello World Start Up!");
        System.in.read();

    }
}
