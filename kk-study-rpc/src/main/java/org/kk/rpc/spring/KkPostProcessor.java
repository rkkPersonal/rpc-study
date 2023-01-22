package org.kk.rpc.spring;

import org.kk.rpc.spring.annotation.KkRpcReference;
import org.kk.rpc.spring.annotation.KkRpcService;
import org.kk.rpc.spring.config.ProtocolConfig;
import org.kk.rpc.spring.config.ReferenceConfig;
import org.kk.rpc.spring.config.RegistryConfig;
import org.kk.rpc.spring.config.ServiceConfig;
import org.kk.rpc.utils.KkRpcBootStrap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;

/**
 * @author Steven
 */
public class KkPostProcessor implements ApplicationContextAware, InstantiationAwareBeanPostProcessor {

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        initProviderConfig(bean);
        initConsumerConfig(bean);

        return bean;
    }
    private void initProviderConfig(Object bean) {

        if (!bean.getClass().isAnnotationPresent(KkRpcService.class)) {
            return;
        }
        System.out.println("找到了需要开放网络访问的service实现类。构建serviceConfig配置");
        // init config
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.addProtocolConfig(applicationContext.getBean(ProtocolConfig.class));
        serviceConfig.addRegistryConfig(applicationContext.getBean(RegistryConfig.class));
        serviceConfig.setReference(bean);

        KkRpcService kkRpcService = bean.getClass().getAnnotation(KkRpcService.class);
        if (void.class == kkRpcService.interfaceClass()) {
            serviceConfig.setService(bean.getClass().getInterfaces()[0]);
        } else {
            serviceConfig.setService(kkRpcService.interfaceClass());
        }

        KkRpcBootStrap.export(serviceConfig);
    }

    private void initConsumerConfig(Object bean) {
        for (Field declaredField : bean.getClass().getDeclaredFields()) {
            declaredField.setAccessible(true);
            if (!declaredField.isAnnotationPresent(KkRpcReference.class)) {
                continue;
            }
            // 引用相关 配置 保存在一个对象里边 // TODO 思考：如果一个引用需要在多个类被使用
            ReferenceConfig referenceConfig = new ReferenceConfig();
            referenceConfig.addRegistryConfig(applicationContext.getBean(RegistryConfig.class));
            referenceConfig.addProtocolConfig(applicationContext.getBean(ProtocolConfig.class));
            referenceConfig.setService(declaredField.getType());

            KkRpcReference kkRpcReference = declaredField.getAnnotation(KkRpcReference.class);
            referenceConfig.setLoadbalance(kkRpcReference.loadBalance());


        }
    }


}
