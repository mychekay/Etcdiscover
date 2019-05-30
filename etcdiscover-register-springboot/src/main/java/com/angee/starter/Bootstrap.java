package com.angee.starter;

import com.angee.etcd.bean.Instance;
import com.angee.etcd.core.Register;
import com.angee.etcd.util.IPUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public class Bootstrap implements InitializingBean {

    private Register<Instance> register;
    private Environment environment;

    public Bootstrap(Register<Instance> register, Environment environment) {
        this.register = register;
        this.environment = environment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Instance instance = buildInstance();
        register.register(instance.getServiceName(), instance);
    }

    Instance buildInstance() throws Exception {
        Instance instance = new Instance();
        instance.setHost(IPUtils.getIpAddress());
        instance.setPort(getTomcatPort());
        instance.setServiceName(getApplicationName());
        return instance;
    }

    int getTomcatPort() throws MalformedObjectNameException {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        return Integer.valueOf(objectNames.iterator().next().getKeyProperty("port"));
    }

    String getApplicationName() {
        return environment.getProperty("spring.application.name");
    }


}
