package com.zhangmen.autoconfigure;

import com.zhangmen.etcd.jetcd.KVer;
import com.zhangmen.etcd.jetcd.Leaser;
import com.zhangmen.etcd.core.Register;
import com.zhangmen.etcd.core.impl.RegisterImpl;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.Lease;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Configuration
@AllArgsConstructor
public class RegisterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Client.class)
    public Client client(ServerPropertiesBean serverPropertiesBean) {
        return Client.builder().endpoints(serverPropertiesBean.getEndpoints()).build();
    }

    @Bean
    @ConditionalOnMissingBean(KVer.class)
    public KVer kver(Client client) {
        KV kv = client.getKVClient();
        return new KVer(kv);
    }

    @Bean
    @ConditionalOnMissingBean(Leaser.class)
    public Leaser leaser(Client client) {
        Lease lease = client.getLeaseClient();
        return new Leaser(lease);
    }

    @Bean
    @ConditionalOnMissingBean(Register.class)
    public Register register(KVer kver,
                             Leaser leaser,
                             LeasePropertiesBean leasePropertiesBean) {
        return new RegisterImpl(kver, leaser, leasePropertiesBean.getTtl());
    }
}
