package com.angee.etcd.balanced;

import com.angee.etcd.balanced.algorithm.*;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public enum BalancedStrategy {
    /**
     * 轮询
     */
    ROUND(Round.getInstance()),

    /**
     * 权重轮询
     */
    WEIGHTED(Weighted.getInstance()),

    /**
     * 随机
     */
    RANDOM(Random.getInstance()),

    /**
     * 哈希
     */
    HASH(Hash.getInstance()),

    /**
     * 一致性哈希
     */
    CONSITENT_HASH(ConsistentHash.getInstance());

    private BalancedAlgorithm balancedAlgorithm;

    BalancedStrategy(BalancedAlgorithm balancedAlgorithm) {
        this.balancedAlgorithm = balancedAlgorithm;
    }

    public BalancedAlgorithm getBalancedAlgorithm() {
        return balancedAlgorithm;
    }
}
