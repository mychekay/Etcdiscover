package com.angee.autoconfigure;

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



/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Configuration
public class RegisterAutoConfiguration {
    private final ServerPropertiesBean serverPropertiesBean;
    private final LeasePropertiesBean leasePropertiesBean;

    public RegisterAutoConfiguration(ServerPropertiesBean serverPropertiesBean, LeasePropertiesBean leasePropertiesBean) {
        this.serverPropertiesBean = serverPropertiesBean;
        this.leasePropertiesBean = leasePropertiesBean;
    }

    @Bean
    @ConditionalOnMissingBean(Client.class)
    public Client client() {
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
                             Leaser leaser) {
        return new RegisterImpl(kver, leaser, leasePropertiesBean.getTtl());
    }

}
