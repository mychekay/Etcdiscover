package com.angee.etcd.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.NonNull;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-31
 */
//TODO 工厂模式
public class Serializer {
    private Scheme scheme;

    public Serializer(Scheme scheme) {
        this.scheme = scheme;
    }

    public String serialize(@NonNull Object source) throws Exception {
        switch (scheme) {
            case JACKSON:
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(source);
            case GSON:
                Gson gson = new Gson();
                return gson.toJson(source);
            case FAST_JSON:
            default:
                return JSONObject.toJSONString(source);
        }
    }

    public <T> T deserialize(String source, Class<T> type) throws Exception {
        switch (scheme) {
            case JACKSON:
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(source, type);
            case GSON:
                Gson gson = new Gson();
                return gson.fromJson(source, type);
            case FAST_JSON:
            default:
                return JSONObject.parseObject(source, type);
        }
    }

    public enum Scheme {
        FAST_JSON,
        JACKSON,
        GSON
    }
}
