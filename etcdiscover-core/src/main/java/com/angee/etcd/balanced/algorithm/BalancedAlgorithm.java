package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.bean.AbstractInstance;
import com.angee.etcd.exception.NoSuchAlgorithmException;

import java.util.List;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@FunctionalInterface
public interface BalancedAlgorithm {
    AbstractInstance apply(Object reqParams, List<AbstractInstance> instances) throws NoSuchAlgorithmException;
}
