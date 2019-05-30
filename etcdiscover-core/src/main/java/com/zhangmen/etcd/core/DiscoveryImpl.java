package com.zhangmen.etcd.core;

import com.zhangmen.etcd.balanced.BalancedStrategy;
import com.zhangmen.etcd.balanced.algorithm.BalancedAlgorithm;
import com.zhangmen.etcd.bean.AbstractInstance;
import com.zhangmen.etcd.bean.Instance;
import com.zhangmen.etcd.consts.ServiceKeyPrefix;
import com.zhangmen.etcd.jetcd.KVer;
import com.zhangmen.etcd.jetcd.Watcher;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.watch.WatchEvent;
import io.etcd.jetcd.watch.WatchResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Slf4j
public class DiscoveryImpl implements Discovery {
    private ConcurrentHashMap<String, InstanceRepository> instanceTable = new ConcurrentHashMap<>(1 << 8);
    private KVer kVer;
    private Watcher watcher;

    public DiscoveryImpl(KVer kVer, Watcher watcher) {
        this.kVer = kVer;
        this.watcher = watcher;
        initInstances();
        listenAll();
        System.out.println(instanceTable);
    }

    private void initInstances() {
        try {
            Set<AbstractInstance> all = kVer.getAll();
            for (AbstractInstance instance : all) {
                this.instanceTable.compute(instance.getServiceName(), (serviceName, repository) -> {
                    if (repository == null) repository = new InstanceRepository(serviceName);
                    repository.add(instance);
                    return repository;
                });
            }
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public AbstractInstance discover(String serviceName, BalancedAlgorithm balancedAlgorithm) {
        try {
            return this.instanceTable.get(serviceName).findByAlgorithm(serviceName, balancedAlgorithm);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public AbstractInstance discover(String serviceName, BalancedStrategy balancedStrategy) {
        try {
            return this.instanceTable.get(serviceName).findByAlgorithm(serviceName, balancedStrategy.getAlgorithmHandler());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    void listenAll() {
        listen(ServiceKeyPrefix.prefix, listener());
    }

    public void listen(String commonServiceNamePrefix, Watch.Listener listener) {
        watcher.watchAll(commonServiceNamePrefix, listener);
    }

    private Watch.Listener listener() {
        Consumer<WatchResponse> onNext = watchResponse -> {
            for (WatchEvent watchEvent : watchResponse.getEvents()) {
                Instance instance = AbstractInstance.fromByteSequence(watchEvent.getKeyValue(), Instance.class);
                if (instance == null) continue;
                if (watchEvent.getEventType() == WatchEvent.EventType.PUT) {
                    instanceTable.compute(instance.getServiceName(), (serviceName, repository) -> {
                        if (null == repository) repository = new InstanceRepository(serviceName);
                        repository.add(instance);
                        return repository;
                    });
                }
                if (watchEvent.getEventType() == WatchEvent.EventType.DELETE) {
                    instanceTable.computeIfPresent(instance.getServiceName(), (serviceName, repository) -> {
                        repository.remove(instance);
                        return repository;
                    });
                }
            }
        };
        Consumer<Throwable> onError = throwable -> log.error(throwable.getMessage(), throwable);
        Runnable onCompleted = () -> log.info("on complete");
        return Watch.listener(onNext, onError, onCompleted);
    }
}
