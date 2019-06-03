package com.angee.etcd.core;

import com.angee.etcd.balanced.algorithm.BalancedAlgorithm;
import com.angee.etcd.core.instance.AbstractInstance;
import com.angee.etcd.exception.NoSuchAlgorithmException;
import lombok.NonNull;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.*;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
@NotThreadSafe
public class InstanceRepository extends AbstractRepository {
    private String serviceName;
    private List<AbstractInstance> instanceList;
    private Set<AbstractInstance> instanceSet;

    public InstanceRepository(String serviceName) {
        this(serviceName, new ArrayList<>(12));
    }

    public InstanceRepository(String serviceName, Collection<? extends AbstractInstance> instances) {
        this.serviceName = serviceName;
        this.instanceList = new ArrayList<>(instances);
        this.instanceSet = new HashSet<>(16);
    }

    @Override
    public boolean add(@NonNull AbstractInstance instance) {
        if (instanceSet.add(instance)) {
            instanceList.add(instance);
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<AbstractInstance> instances) {
        instanceSet.addAll(instances);
        instanceList = new ArrayList<>(instanceSet);
        return true;
    }

    @Override
    public boolean remove(@NonNull AbstractInstance instance) {
        if (instanceSet.remove(instance)) {
            instanceList.remove(instance);
        }
        return true;
    }

    @Override
    public AbstractInstance findByAlgorithm(String serviceName, BalancedAlgorithm balancedAlgorithm) throws NoSuchAlgorithmException {
        return balancedAlgorithm.apply(serviceName, instanceList);
    }

    @Override
    public Collection<AbstractInstance> findAll(String serviceName) {
        return instanceList;
    }

    @Override
    public String getServiceName() {
        return this.serviceName;
    }

}
