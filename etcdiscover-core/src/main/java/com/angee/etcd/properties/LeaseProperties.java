package com.angee.etcd.properties;

import lombok.Data;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Data
public class LeaseProperties {
    /**
     * seconds
     */
    private long ttl = 30;
}
