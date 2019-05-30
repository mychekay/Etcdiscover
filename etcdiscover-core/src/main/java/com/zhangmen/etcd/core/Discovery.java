package com.zhangmen.etcd.core;

import com.zhangmen.etcd.balanced.BalancedStrategy;
import com.zhangmen.etcd.balanced.algorithm.BalancedAlgorithm;
import com.zhangmen.etcd.bean.AbstractInstance;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public interface Discovery {
    AbstractInstance discover(String serviceName, BalancedAlgorithm custom);

    AbstractInstance discover(String serviceName, BalancedStrategy balancedStrategy);
}
