package com.zhangmen.etcd.core;

import com.zhangmen.etcd.balanced.algorithm.BalancedAlgorithm;
import com.zhangmen.etcd.bean.AbstractInstance;

import java.util.Collection;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
public interface Repository {
    boolean add(AbstractInstance instance);

    boolean remove(AbstractInstance instance);

    AbstractInstance findByAlgorithm(String serviceName, BalancedAlgorithm balancedAlgorithm);

    Collection<AbstractInstance> findAll(String serviceName);
}
