package com.angee.autoconfigure;

import com.angee.etcd.config.KVProperties;
import com.angee.etcd.config.LeaseProperties;
import com.angee.etcd.config.ServerProperties;
import com.angee.etcd.config.WatchProperties;
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
@ConfigurationProperties(prefix = "etcdiscover.discover.client")
public class DiscoverClientProperties {
    private ServerProperties server;

    private KVProperties kv;

    private LeaseProperties lease;

    private WatchProperties watch;
}
