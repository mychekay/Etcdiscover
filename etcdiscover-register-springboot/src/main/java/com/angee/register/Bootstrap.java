package com.angee.register;

import com.angee.etcd.bean.Instance;
import com.angee.etcd.core.Register;
import com.angee.etcd.util.IPUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;

import javax.management.MalformedObjectNameException;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Order
public class Bootstrap
        implements ImportBeanDefinitionRegistrar,
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

    protected Instance buildInstance() throws Exception {
        Instance instance = new Instance();
        instance.setHost(IPUtils.getIpAddress());
        try {
            instance.setPort(instanceConf.getTomcatPort());
        } catch (MalformedObjectNameException e) {
            throw new Exception("cannot find tomcat port");
        }
        instance.setServiceName(StringUtils.isEmpty(serviceName) ? instanceConf.getApplicationName() : serviceName);
        return instance;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Bootstrap.class);
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
