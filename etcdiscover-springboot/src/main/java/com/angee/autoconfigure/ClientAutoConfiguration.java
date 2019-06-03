package com.angee.autoconfigure;

import com.angee.autoconfigure.properties.ClientProperties;
import com.angee.autoconfigure.properties.HealthCheckPropBean;
import com.angee.etcd.config.ServerProperties;
import com.angee.etcd.core.Discovery;
import com.angee.etcd.core.DiscoveryImpl;
import com.angee.etcd.core.Register;
import com.angee.etcd.core.RegisterImpl;
import com.angee.etcd.core.instance.Instance;
import com.angee.etcd.jetcd.KVer;
import com.angee.etcd.jetcd.Leaser;
import com.angee.etcd.jetcd.Watcher;
import com.angee.etcd.util.ByteSequenceUtil;
import com.angee.etcd.util.pool.ThreadPoolUtil;
import io.etcd.jetcd.*;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
@Import({ClientProperties.class, HealthCheckPropBean.class})
public class ClientAutoConfiguration {
    private final ClientProperties properties;
    private final HealthCheckPropBean healthCheckPropBean;

    @Bean
    @ConditionalOnMissingBean(Client.class)
    public Client client() {
        ClientBuilder clientBuilder = Client.builder().endpoints(properties.getServer().getEndpoints());

        String username = properties.getServer().getUsername();
        String password = properties.getServer().getPassword();

        ServerProperties.ConnectionManager connectionManager = properties.getServer().getConnectionPool();
        int poolMaxSize = connectionManager.getPoolMaxSize();
        long keepAliveTime = connectionManager.getKeepAliveTimeMillis();
        int blockingCapcity = connectionManager.getBlockingCapacity();
        if (!StringUtils.isEmpty(username)) clientBuilder.user(ByteSequenceUtil.fromString(username));
        if (!StringUtils.isEmpty(password)) clientBuilder.password(ByteSequenceUtil.fromString(password));
        clientBuilder.executorService(ThreadPoolUtil.fifoFixedPool(poolMaxSize, keepAliveTime, blockingCapcity));

        return clientBuilder.build();
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
    @ConditionalOnMissingBean(Leaser.class)
    public Leaser leaser(Client client) {
        Lease lease = client.getLeaseClient();
        return new Leaser(lease);
    }

    @Bean
    @ConditionalOnMissingBean(Register.class)
    public Register<Instance> register(KVer<Instance> kver,
                                       Leaser leaser) {
        return new RegisterImpl(kver, leaser, properties.getLease().getTtl());
    }

    @Bean
    @ConditionalOnBean({KVer.class, Watcher.class})
    @ConditionalOnMissingBean(Discovery.class)
    public Discovery discovery(KVer kv, Watcher watch) {
        return new DiscoveryImpl(kv, watch, healthCheckPropBean);
    }

}
