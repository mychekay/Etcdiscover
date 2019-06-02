package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.bean.AbstractInstance;

import java.util.List;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-02
 */
public class Weighted implements BalancedAlgorithm{

    private static Weighted ourInstance = new Weighted();

    public static Weighted getInstance() { return ourInstance; }

    private Weighted() {
    }

    @Override
    public AbstractInstance apply(Object reqParams, List<AbstractInstance> instances) {
        return null;
    }
}
