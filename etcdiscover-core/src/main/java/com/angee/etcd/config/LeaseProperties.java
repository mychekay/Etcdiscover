package com.angee.etcd.config;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Getter
@Setter
public class LeaseProperties {
    /**
     * lease ttl seconds
     */
    private long ttl = 60;
}
