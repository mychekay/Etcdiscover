package com.zhangmen.etcd.balanced.algorithm;

import com.zhangmen.etcd.bean.AbstractInstance;

import java.util.Collection;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
public class Hash implements BalancedAlgorithm {
    private static Hash ourInstance = new Hash();

    public static Hash getInstance() {
        return ourInstance;
    }

    private Hash() {
    }

    @Override
    public AbstractInstance apply(String serviceName, Collection<AbstractInstance> instances) {
        return null;
    }
}
