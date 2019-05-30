package com.zhangmen.etcd.balanced.algorithm;

import com.zhangmen.etcd.bean.AbstractInstance;

import java.util.Collection;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@FunctionalInterface
public interface BalancedAlgorithm {
    AbstractInstance apply(Collection<AbstractInstance> instances);
}
