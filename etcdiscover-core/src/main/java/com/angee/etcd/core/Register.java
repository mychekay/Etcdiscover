package com.angee.etcd.core;

import com.angee.etcd.bean.AbstractInstance;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public interface Register<T extends AbstractInstance> {
    boolean register(String serviceName, T value);

    boolean update(String serviceName, T value);

    boolean unReg(String serviceName, T value);
}
