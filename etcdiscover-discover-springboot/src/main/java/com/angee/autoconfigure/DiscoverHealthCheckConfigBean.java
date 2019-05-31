package com.angee.autoconfigure;

import com.angee.etcd.core.DiscoveryImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-31
 */
@Component
@ConfigurationProperties(prefix = "etcd.discover.healthcheck")
public class DiscoverHealthCheckConfigBean extends DiscoveryImpl.DiscoverHealthCheckConfig {
}
