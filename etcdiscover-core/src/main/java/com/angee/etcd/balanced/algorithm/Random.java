package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.core.instance.AbstractInstance;

import java.util.List;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public class Random implements BalancedAlgorithm {
    private static Random ourInstance = new Random();

    public static Random getInstance() {
        return ourInstance;
    }

    private Random() {
    }

    @Override
    public AbstractInstance apply(Object reqParams, List<AbstractInstance> instances) {
        int randomIndex = new java.util.Random().nextInt(instances.size());
        return instances.get(randomIndex);
    }
}
