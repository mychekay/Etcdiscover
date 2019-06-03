package com.angee.autoconfigure;

import com.angee.etcd.config.KVProperties;
import com.angee.etcd.config.LeaseProperties;
import com.angee.etcd.config.ServerProperties;
import com.angee.etcd.config.WatchProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
@Data
@Component
@ConfigurationProperties(prefix = "etcdiscover.client")
public class ClientProperties {
    /**
     * etcd server config
     */
    @NestedConfigurationProperty
    private ServerProperties server;

    /**
     * etcd kv config
     */
    @NestedConfigurationProperty
    private KVProperties kv;

    /**
     * etcd lease config
     */
    @NestedConfigurationProperty
    private LeaseProperties lease;

    /**
     * etcd watch config
     */
    @NestedConfigurationProperty
    private WatchProperties watch;
}
