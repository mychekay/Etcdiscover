package com.angee.etcd.core;

import com.angee.etcd.anntotation.NoBug;
import com.angee.etcd.balanced.BalancedStrategy;
import com.angee.etcd.balanced.algorithm.BalancedAlgorithm;
import com.angee.etcd.consts.KeyDirectory;
import com.angee.etcd.core.instance.AbstractInstance;
import com.angee.etcd.core.instance.Instance;
import com.angee.etcd.health.AutoRefresh;
import com.angee.etcd.jetcd.KVer;
import com.angee.etcd.jetcd.Watcher;
import com.angee.etcd.util.serialize.Scheme;
import com.angee.etcd.util.serialize.SerializerFactory;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.watch.WatchEvent;
import io.etcd.jetcd.watch.WatchResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Slf4j
@NoBug
public class DiscoveryImpl implements Discovery<Instance>, AutoRefresh {
    private ConcurrentHashMap<String, InstanceRepository> instanceTable = new ConcurrentHashMap<>(1 << 8);
    private KVer<Instance> kVer;
    private Watcher watcher;
    private DiscoverHealthCheckConfig discoverHealthCheckConfig;

    public DiscoveryImpl(KVer<Instance> kVer,
                         Watcher watcher,
                         DiscoverHealthCheckConfig discoverHealthCheckConfig) {
        this.kVer = kVer;
        this.watcher = watcher;
        this.discoverHealthCheckConfig = discoverHealthCheckConfig;
        afterConstruct();
    }

    private void afterConstruct() {
        this.refresh();
        this.listenAll();
    }

    private void updateInstancesTb(Collection<Instance> allInstances) {
        if (null == allInstances) return;
        for (AbstractInstance instance : allInstances) {
            this.instanceTable.compute(instance.getServiceName(), (serviceName, repository) -> {
                if (repository == null) repository = new InstanceRepository(serviceName);
                repository.add(instance);
                return repository;
            });
        }
    }

    @Override
    public Instance discover(String serviceName, BalancedAlgorithm balancedAlgorithm) {
        try {
            return (Instance) this.instanceTable.get(serviceName).findByAlgorithm(serviceName, balancedAlgorithm);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Instance discover(String serviceName, BalancedStrategy balancedStrategy) {
        try {
            return (Instance) this.instanceTable.get(serviceName).findByAlgorithm(serviceName, balancedStrategy.getBalancedAlgorithm());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    void listenAll() {
        this.listen(KeyDirectory.buildDir(KeyDirectory.First.SERVICE), listener());
    }

    public void listen(String commonServiceNamePrefix, Watch.Listener listener) {
        watcher.watchAll(commonServiceNamePrefix, listener);
    }

    private Watch.Listener listener() {
        Consumer<WatchResponse> onNext = watchResponse -> {
            for (WatchEvent watchEvent : watchResponse.getEvents()) {
                try {
                    if (watchEvent.getEventType() == WatchEvent.EventType.PUT) {
                        KeyValue keyValue = watchEvent.getKeyValue();
                        Instance instance = SerializerFactory.create(Scheme.FAST_JSON).deserialize(keyValue.getValue().getBytes(), Instance.class);
                        if (instance == null) continue;
                        instanceTable.compute(instance.getServiceName(), (serviceName, repository) -> {
                            if (null == repository) repository = new InstanceRepository(serviceName);
                            repository.add(instance);
                            return repository;
                        });
                    } else if (watchEvent.getEventType() == WatchEvent.EventType.DELETE) {
                        KeyValue prevKeyVal = watchEvent.getPrevKV();
                        Instance instance = SerializerFactory.create(Scheme.FAST_JSON).deserialize(prevKeyVal.getValue().getBytes(), Instance.class);
                        if (instance == null) continue;
                        instanceTable.computeIfPresent(instance.getServiceName(), (serviceName, repository) -> {
                            repository.remove(instance);
                            return repository;
                        });
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        };
        Consumer<Throwable> onError = throwable -> log.error(throwable.getMessage(), throwable);
        Runnable onCompleted = () -> log.info("on complete");
        return Watch.listener(onNext, onError, onCompleted);
    }

    @Override
    public void refresh() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                DiscoveryImpl.this.updateInstancesTb(kVer.getAll(Instance.class));
            }
        }, discoverHealthCheckConfig.getDelay(), discoverHealthCheckConfig.getPeriod());
    }

    public static class DiscoverHealthCheckConfig {
        private long delay = 0;
        private long period = 15 * 1000;

        public long getDelay() {
            return delay;
        }

        public void setDelay(long delay) {
            this.delay = delay;
        }

        public long getPeriod() {
            return period;
        }

        public void setPeriod(long period) {
            this.period = period;
        }
    }
}
