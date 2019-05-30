package com.zhangmen.etcd.balanced;

import com.zhangmen.etcd.balanced.algorithm.*;
import lombok.Getter;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public enum BalancedStrategy {
    /**
     * 轮询
     */
    POLL(Poll.getInstance()),

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

    @Getter
    private BalancedAlgorithm balancedAlgorithm;

    BalancedStrategy(BalancedAlgorithm balancedAlgorithm) {
        this.balancedAlgorithm = balancedAlgorithm;
    }

    public BalancedAlgorithm getAlgorithmHandler() {
        return this.getBalancedAlgorithm();
    }
}
