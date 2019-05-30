package com.zhangmen.autoconfigure;

import com.zhangmen.etcd.properties.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Component
@ConfigurationProperties(prefix = "etcd.server")
public class ServerPropertiesBean extends ServerProperties {
}
