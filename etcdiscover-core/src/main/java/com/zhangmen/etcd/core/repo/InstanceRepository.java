package com.zhangmen.etcd.core.repo;

import com.zhangmen.etcd.balanced.algorithm.BalancedAlgorithm;
import com.zhangmen.etcd.bean.AbstractInstance;
import com.zhangmen.etcd.core.AbstractRepository;
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
    private List<String> instanceIdList ;

    public InstanceRepository(String serviceName) {
        this.serviceName = serviceName;
        this.instanceIdList = new ArrayList<>(32);
    }

    @Override
    public boolean add(@NonNull AbstractInstance instance) {
        String instanceID = instance.getInstanceID();
        if (instanceMap.containsKey(instanceID))
            return true;
        instanceIdList.add(instanceID);
        instanceMap.put(instanceID, instance);
        return true;
    }

    @Override
    public boolean remove(@NonNull AbstractInstance instance) {
        String instanceID = instance.getInstanceID();
        if (!instanceMap.containsKey(instanceID))
            return true;
        instanceIdList.remove(instanceID);
        instanceMap.remove(instanceID);
        return true;
    }

    @Override
    public AbstractInstance findByAlgorithm(String serviceName, BalancedAlgorithm balancedAlgorithm) {
        return null;
    }

    @Override
    public Collection<AbstractInstance> findAll(String serviceName) {
        return instanceMap.values();
    }

}
