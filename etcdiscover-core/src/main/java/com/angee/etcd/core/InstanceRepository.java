package com.angee.etcd.core;

import com.angee.etcd.balanced.algorithm.BalancedAlgorithm;
import com.angee.etcd.bean.AbstractInstance;
import lombok.NonNull;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
@NotThreadSafe
public class InstanceRepository extends AbstractRepository {
    private Set<AbstractInstance> instanceSet = new HashSet<>(32);
    private String serviceName = "";

    public InstanceRepository(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean add(@NonNull AbstractInstance instance) {
        instanceSet.add(instance);
        return true;
    }

    @Override
    public boolean addAll(Collection<AbstractInstance> instances) {
        instanceSet.addAll(instances);
        return false;
    }

    @Override
    public boolean remove(@NonNull AbstractInstance instance) {
        instanceSet.remove(instance);
        return true;
    }

    @Override
    public AbstractInstance findByAlgorithm(String serviceName, BalancedAlgorithm balancedAlgorithm) {
        return balancedAlgorithm.apply(serviceName, instanceSet);
    }

    @Override
    public Collection<AbstractInstance> findAll(String serviceName) {
        return instanceSet;
    }

    @Override
    public String getServiceName() {
        return this.serviceName;
    }
}
