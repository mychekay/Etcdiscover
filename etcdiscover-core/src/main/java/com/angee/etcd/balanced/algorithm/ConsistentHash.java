package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.bean.AbstractInstance;

import java.util.Collection;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
public class ConsistentHash implements BalancedAlgorithm {
    private static ConsistentHash ourInstance = new ConsistentHash();

    public static ConsistentHash getInstance() {
        return ourInstance;
    }

    private ConsistentHash() {
    }

    @Override
    public AbstractInstance apply(String serviceName, Collection<AbstractInstance> instances) {
        return null;
    }
}
