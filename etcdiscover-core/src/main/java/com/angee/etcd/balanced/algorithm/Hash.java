package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.bean.AbstractInstance;
import com.angee.etcd.util.HashFunction;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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
    public AbstractInstance apply(Object reqParams, List<AbstractInstance> instances) throws NoSuchAlgorithmException {
        long hashcode = HashFunction.hash(reqParams.toString());
        return instances.get((int) (hashcode % instances.size()));
    }

}
