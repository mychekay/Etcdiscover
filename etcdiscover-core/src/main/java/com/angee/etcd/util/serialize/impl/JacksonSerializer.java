package com.angee.etcd.util.serialize.impl;

import com.angee.etcd.exception.DeserializeException;
import com.angee.etcd.exception.SerializeException;
import com.angee.etcd.util.serialize.AbstractSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
public class JacksonSerializer extends AbstractSerializer {
    private JacksonSerializer() {}

    private static JacksonSerializer singleton = new JacksonSerializer();

    public static JacksonSerializer getInstance() { return singleton; }

    @Override
    public String serialize(@NonNull Object source) throws SerializeException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(source);
        } catch (Exception e) {
            throw new SerializeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public <T> T deserialize(String source, Class<T> type) throws DeserializeException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(source, type);
        } catch (Exception e) {
            throw new DeserializeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public <T> T deserialize(byte[] source, Class<T> type) throws DeserializeException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(source, type);
        } catch (Exception e) {
            throw new DeserializeException(e.getMessage(), e.getCause());
        }
    }
}
