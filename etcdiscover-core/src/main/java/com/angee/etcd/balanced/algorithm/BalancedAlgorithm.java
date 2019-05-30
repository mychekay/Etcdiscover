package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.bean.AbstractInstance;

import java.util.Collection;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@FunctionalInterface
public interface BalancedAlgorithm {
    AbstractInstance apply(String serviceName, Collection<AbstractInstance> instances);
}
