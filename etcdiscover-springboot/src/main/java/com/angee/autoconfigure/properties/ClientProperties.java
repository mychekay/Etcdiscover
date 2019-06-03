package com.angee.autoconfigure.properties;

import com.angee.etcd.config.LeaseProperties;
import com.angee.etcd.config.ServerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
@Data
@Component
@Import({ServerProperties.class, LeaseProperties.class})
@ConfigurationProperties(prefix = "etcdiscover.client")
public class ClientProperties {
    /**
     * etcd server config
     */
    @NestedConfigurationProperty
    private ServerProperties server = new ServerProperties();

    /**
     * etcd lease config
     */
    @NestedConfigurationProperty
    private LeaseProperties lease = new LeaseProperties();

}
