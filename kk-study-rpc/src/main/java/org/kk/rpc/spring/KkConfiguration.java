package org.kk.rpc.spring;

import org.kk.rpc.spring.config.ProtocolConfig;
import org.kk.rpc.spring.config.RegistryConfig;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Field;

/**
 * @author Steven
 */

public class KkConfiguration implements ImportBeanDefinitionRegistrar {

    StandardEnvironment standardEnvironment;

    public KkConfiguration(Environment environment) {
        this.standardEnvironment = (StandardEnvironment) environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        BeanDefinitionBuilder protocol = BeanDefinitionBuilder.genericBeanDefinition(ProtocolConfig.class);
        for (Field declaredField : ProtocolConfig.class.getDeclaredFields()) {
            declaredField.setAccessible(true);
            protocol.addPropertyValue(declaredField.getName(), standardEnvironment.getProperty("kkrpc.protocol." + declaredField.getName()));
        }

        registry.registerBeanDefinition("protocolConfig", protocol.getBeanDefinition());


        BeanDefinitionBuilder register = BeanDefinitionBuilder.genericBeanDefinition(RegistryConfig.class);
        for (Field declaredField : RegistryConfig.class.getDeclaredFields()) {
            declaredField.setAccessible(true);
            register.addPropertyValue(declaredField.getName(), standardEnvironment.getProperty("kkrpc.register." + declaredField.getName()));
        }
        registry.registerBeanDefinition("RegistryConfig", register.getBeanDefinition());
    }
}
