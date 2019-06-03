package com.angee.etcd.util.serialize.impl;

import com.angee.etcd.exception.DeserializeException;
import com.angee.etcd.exception.SerializeException;
import com.angee.etcd.util.serialize.AbstractSerializer;
import com.google.gson.Gson;
import lombok.NonNull;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
public class GsonSerializer extends AbstractSerializer {
    private GsonSerializer() {}

    private static GsonSerializer singleton = new GsonSerializer();

    public static GsonSerializer getInstance() { return singleton; }

    @Override
    public String serialize(@NonNull Object source) throws SerializeException {
        try {
            Gson gson = new Gson();
            return gson.toJson(source);
        } catch (Exception e) {
            throw new SerializeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public <T> T deserialize(String source, Class<T> type) throws DeserializeException {
        try {
            Gson gson = new Gson();
            return gson.fromJson(source, type);
        } catch (Exception e) {
            throw new DeserializeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public <T> T deserialize(byte[] source, Class<T> type) throws DeserializeException {
        try {
            return deserialize(new String(source), type);
        } catch (Exception e) {
            throw new DeserializeException(e.getMessage(), e.getCause());
        }
    }
}
