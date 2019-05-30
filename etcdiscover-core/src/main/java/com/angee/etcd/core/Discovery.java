package com.angee.etcd.core;

import com.angee.etcd.balanced.BalancedStrategy;
import com.angee.etcd.balanced.algorithm.BalancedAlgorithm;
import com.angee.etcd.bean.AbstractInstance;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public interface Discovery<T extends AbstractInstance> {
    T discover(String serviceName, BalancedAlgorithm custom);

    T discover(String serviceName, BalancedStrategy balancedStrategy);
}
