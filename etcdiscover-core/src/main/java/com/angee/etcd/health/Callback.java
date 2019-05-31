package com.angee.etcd.health;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-31
 */
@FunctionalInterface
public interface Callback<T> {
    void callback(T t);
}
