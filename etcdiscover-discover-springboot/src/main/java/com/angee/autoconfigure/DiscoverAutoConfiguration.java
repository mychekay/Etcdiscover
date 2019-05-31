package com.angee.autoconfigure;

import com.angee.etcd.core.Discovery;
import com.angee.etcd.core.DiscoveryImpl;
import com.angee.etcd.jetcd.KVer;
import com.angee.etcd.jetcd.Watcher;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.Watch;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Configuration
@AllArgsConstructor
@Import({ServerPropertiesBean.class, DiscoveryImpl.DiscoverHealthCheckConfig.class})
public class DiscoverAutoConfiguration {
    private final ServerPropertiesBean serverPropertiesBean;
    private final DiscoveryImpl.DiscoverHealthCheckConfig discoverHealthCheckConfig;

    @Bean
    @ConditionalOnMissingBean(Client.class)
    public Client client() {
        return Client.builder().endpoints(serverPropertiesBean.getEndpoints()).build();
    }

    @Bean
    @ConditionalOnMissingBean(KVer.class)
    public KVer kver(Client client) {
        KV kv = client.getKVClient();
        return new KVer<>(kv);
    }

    @Bean
    @ConditionalOnMissingBean(Watcher.class)
    public Watcher watcher(Client client) {
        Watch watch = client.getWatchClient();
        return new Watcher(watch);
    }

    @Bean
    @ConditionalOnBean({KVer.class, Watcher.class})
    @ConditionalOnMissingBean(Discovery.class)
    public Discovery discovery(KVer kv, Watcher watch) {
        return new DiscoveryImpl(kv, watch, discoverHealthCheckConfig);
    }

}
