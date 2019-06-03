package com.angee.autoconfigure;

import com.angee.etcd.config.LeaseProperties;
import com.angee.etcd.config.ServerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
@Data
@Component
@ConfigurationProperties(prefix = "etcdiscover.provider.client")
public class ProviderClientProperties {
    private ServerProperties server = new ServerProperties();
    private LeaseProperties lease = new LeaseProperties();
}
