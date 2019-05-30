package com.zhangmen.etcd.bean;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.etcd.jetcd.KeyValue;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
@Slf4j
public abstract class AbstractInstance {
    private String instanceID = UUID.randomUUID().toString();
    private String host;
    private int port;
    private String serviceName;

    public String getInstanceID() {
        return instanceID;
    }

    public void setInstanceID(String instanceID) {
        this.instanceID = instanceID;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public static <T> T fromByteSequence(KeyValue keyValue, Class<T> classType) {
        try {
            return JSONObject.parseObject(keyValue.getValue().getBytes(), classType);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

}
