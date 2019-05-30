package com.angee.autoconfigure;

import com.angee.etcd.jetcd.KVer;
import com.angee.etcd.jetcd.Watcher;
import com.angee.etcd.core.Discovery;
import com.angee.etcd.core.DiscoveryImpl;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.Watch;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
public class DiscoverAutoConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Bean
    @ConditionalOnMissingBean(Client.class)
    public Client client() {
        ServerPropertiesBean serverPropertiesBean = beanFactory.getBean(ServerPropertiesBean.class);
        return Client.builder().endpoints(serverPropertiesBean.getEndpoints()).build();
    }

    @Bean
    @ConditionalOnMissingBean(KVer.class)
    public KVer kver(Client client) {
        KV kv = client.getKVClient();
        return new KVer(kv);
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
        return new DiscoveryImpl(kv, watch);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
