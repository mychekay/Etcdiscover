package com.zhangmen.autoconfigure;

import com.zhangmen.etcd.properties.LeaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Component
@ConfigurationProperties(prefix = "etcd.lease")
public class LeasePropertiesBean extends LeaseProperties {
}
