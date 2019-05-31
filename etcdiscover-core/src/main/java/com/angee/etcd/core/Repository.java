package com.angee.etcd.core;

import com.angee.etcd.balanced.algorithm.BalancedAlgorithm;
import com.angee.etcd.bean.AbstractInstance;

import java.util.Collection;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
public interface Repository {
    boolean add(AbstractInstance instance);

    boolean addAll(Collection<AbstractInstance> instances);

    boolean remove(AbstractInstance instance);

    AbstractInstance findByAlgorithm(String serviceName, BalancedAlgorithm balancedAlgorithm);

    Collection<AbstractInstance> findAll(String serviceName);
}
