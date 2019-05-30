package com.zhangmen.etcd.core;

import com.zhangmen.etcd.balanced.algorithm.BalancedAlgorithm;
import com.zhangmen.etcd.bean.AbstractInstance;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
public class AbstractRepository implements Repository {
    protected Map<String, AbstractInstance> instanceMap = new HashMap<>(32);
    protected String serviceName;

    @Override
    public boolean add(AbstractInstance instance) {
        return false;
    }

    @Override
    public boolean remove(AbstractInstance instance) {
        return false;
    }

    @Override
    public AbstractInstance findByAlgorithm(String serviceName, BalancedAlgorithm balancedAlgorithm) {
        return null;
    }

    @Override
    public Collection<AbstractInstance> findAll(String serviceName) {
        return null;
    }
}
