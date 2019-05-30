package com.zhangmen.etcd.anntotation;

import com.zhangmen.etcd.balanced.BalancedStrategy;

import java.lang.annotation.*;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-29
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})

public @interface EtcdClient {
    String serviceName() default "";

    BalancedStrategy balancedStrategy() default BalancedStrategy.POLL;
}
