package com.angee.etcd.util.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.angee.etcd.exception.DeserializeException;
import com.angee.etcd.exception.SerializeException;
import com.angee.etcd.util.serialize.AbstractSerializer;
import lombok.NonNull;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
public class FastjsonSerializer extends AbstractSerializer {
    private FastjsonSerializer() {
    }

    private static FastjsonSerializer singleton = new FastjsonSerializer();

    public static FastjsonSerializer getInstance() { return singleton; }

    @Override
    public String serialize(@NonNull Object source) throws SerializeException {
        try {
            return JSON.toJSONString(source);
        } catch (Exception e) {
            throw new SerializeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public <T> T deserialize(String source, Class<T> type) throws DeserializeException {
        try {
            return JSON.parseObject(source, type);
        } catch (Exception e) {
            throw new DeserializeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public <T> T deserialize(byte[] source, Class<T> type) throws DeserializeException {
        try {
            return JSON.parseObject(source, type);
        } catch (Exception e) {
            throw new DeserializeException(e.getMessage(), e.getCause());
        }
    }
}
