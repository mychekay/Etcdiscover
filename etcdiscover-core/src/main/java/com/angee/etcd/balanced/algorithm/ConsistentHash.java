package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.core.instance.AbstractInstance;

import java.util.List;

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
    public AbstractInstance apply(Object reqParams, List<AbstractInstance> instances) {
        return null;
    }
}
