package org.kk.rpc;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */

public class AppTest {
    /**
     * Rigorous Test :-)
     */

    public void shouldAnswerWithTrue() {


        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("");

        annotationConfigApplicationContext.start();
    }
}
