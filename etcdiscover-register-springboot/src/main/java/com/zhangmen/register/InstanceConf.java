package com.zhangmen.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-29
 */
@Component
public class InstanceConf {
    @Autowired
    Environment environment;

    public int getTomcatPort() throws MalformedObjectNameException {
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        return Integer.valueOf(objectNames.iterator().next().getKeyProperty("port"));
    }

    public String getApplicationName() {
        return environment.getProperty("spring.application.name");
    }
}
