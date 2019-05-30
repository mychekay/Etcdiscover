package com.angee.etcd.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Slf4j
public class Instance extends AbstractInstance {

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
