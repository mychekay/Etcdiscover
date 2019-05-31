package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.bean.AbstractInstance;
import com.angee.etcd.bean.Instance;

import java.util.Collection;
import java.util.Iterator;

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
    public Instance apply(String serviceName, Collection<AbstractInstance> instances) {
        int randomIndex = new java.util.Random().nextInt(instances.size());
        Iterator<AbstractInstance> instanceIterator = instances.iterator();
        AbstractInstance target = null;
        while (instanceIterator.hasNext()) {
            target = instanceIterator.next();
            if (randomIndex-- == 0) break;
        }
        return (Instance) target;
    }
}
