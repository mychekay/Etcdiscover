package com.angee.etcd.core;

import com.angee.etcd.balanced.algorithm.BalancedAlgorithm;
import com.angee.etcd.bean.AbstractInstance;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
public abstract class AbstractRepository implements Repository {
    protected Set<AbstractInstance> instanceSet = new HashSet<>(32);
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
