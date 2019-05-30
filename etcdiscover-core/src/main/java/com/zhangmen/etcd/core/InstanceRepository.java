package com.zhangmen.etcd.core;

import com.zhangmen.etcd.balanced.algorithm.BalancedAlgorithm;
import com.zhangmen.etcd.bean.AbstractInstance;
import lombok.NonNull;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
@NotThreadSafe
public class InstanceRepository extends AbstractRepository {
    private List<String> instanceIdList;

    public InstanceRepository(String serviceName) {
        this.serviceName = serviceName;
        this.instanceIdList = new ArrayList<>(32);
    }

    @Override
    public boolean add(@NonNull AbstractInstance instance) {
        if (instanceSet.contains(instance))
            return true;
        instanceIdList.add(instance.getInstanceID());
        instanceSet.add(instance);
        return true;
    }

    @Override
    public boolean remove(@NonNull AbstractInstance instance) {
        if (!instanceSet.contains(instance))
            return true;
        instanceIdList.remove(instance.getInstanceID());
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

}
