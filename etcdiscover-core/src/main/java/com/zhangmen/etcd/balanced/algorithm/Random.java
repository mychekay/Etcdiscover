package com.zhangmen.etcd.balanced.algorithm;

import com.zhangmen.etcd.bean.AbstractInstance;
import com.zhangmen.etcd.bean.Instance;

import java.util.ArrayList;
import java.util.Collection;
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

    private List<AbstractInstance> instanceList = new ArrayList<>();

    @Override
    public Instance apply(String serviceName, Collection<AbstractInstance> instances) {

        return null;
    }
}
