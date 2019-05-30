package com.zhangmen.register;

import com.zhangmen.etcd.bean.Instance;
import com.zhangmen.etcd.core.Register;
import com.zhangmen.etcd.util.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import javax.management.MalformedObjectNameException;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public class Bootstrap implements ImportBeanDefinitionRegistrar,
        ApplicationContextAware,
        InitializingBean {
    private Register register;
    private InstanceConf instanceConf;
    private String serviceName;

    public Bootstrap() {

    }

    public Bootstrap(String serviceName) {
        this.serviceName = serviceName;
    }

    protected Instance buildInstance() {
        Instance instance = new Instance();
        instance.setHost(IPUtils.getIpAddress());
        try {
            instance.setPort(instanceConf.getTomcatPort());
        } catch (MalformedObjectNameException e) {
            instance.setPort(8080);
        }
        instance.setServiceName(StringUtils.isEmpty(serviceName) ? instanceConf.getApplicationName() : serviceName);
        instance.setWeight("0");
        return instance;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Bootstrap.class);
        builder.addConstructorArgValue(importingClassMetadata.getAnnotatedMethods("serviceName"));
        BeanDefinition beanDefinition = builder.getBeanDefinition();
        registry.registerBeanDefinition("bootstrap", beanDefinition);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.register = applicationContext.getBean(Register.class);
        this.instanceConf = applicationContext.getBean(InstanceConf.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Instance instance = buildInstance();
        register.register(instance.getServiceName(), instance);
    }
}
