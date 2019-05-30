package com.angee.etcd.bean;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.etcd.jetcd.KeyValue;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Slf4j
public class Instance extends AbstractInstance {

    public static Instance fromByteSequence(KeyValue keyValue) {
        try {
            return JSONObject.parseObject(keyValue.getValue().getBytes(), Instance.class);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
