package com.zhangmen.etcd.core;

import com.zhangmen.etcd.bean.AbstractInstance;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public interface Register {
    boolean register(String serviceName, AbstractInstance value);

    boolean update(String serviceName, AbstractInstance value);

    boolean unReg(String serviceName, AbstractInstance value);
}
