package com.angee.autoconfigure;

import com.angee.etcd.bean.Instance;
import com.angee.etcd.core.Register;
import com.angee.etcd.core.RegisterImpl;
import com.angee.etcd.jetcd.KVer;
import com.angee.etcd.jetcd.Leaser;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.Lease;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Configuration("registerAutoConfiguration")
@Import({ProviderClientProperties.class})
public class RegisterAutoConfiguration {
    private final ProviderClientProperties properties;

    public RegisterAutoConfiguration(ProviderClientProperties providerClientProperties) {
        this.properties = providerClientProperties;
    }

    @Bean
    @ConditionalOnMissingBean(Client.class)
    public Client client() {
        return Client.builder().endpoints(properties.getServer().getEndpoints()).build();
    }

    @Bean
    @ConditionalOnMissingBean(KVer.class)
    public KVer<Instance> kver(Client client) {
        KV kv = client.getKVClient();
        return new KVer<>(kv);
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

}
