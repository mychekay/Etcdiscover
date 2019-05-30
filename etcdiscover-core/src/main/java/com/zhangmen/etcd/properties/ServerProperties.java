package com.zhangmen.etcd.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Getter
@Setter
public class ServerProperties {
    private String[] endpoints = {"http://47.100.170.216:2379"};
}
