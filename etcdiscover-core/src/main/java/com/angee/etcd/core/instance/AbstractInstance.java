package com.angee.etcd.core.instance;

import com.google.common.base.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractInstance)) return false;
        AbstractInstance that = (AbstractInstance) o;
        return port == that.port &&
                Objects.equal(instanceID, that.instanceID) &&
                Objects.equal(host, that.host) &&
                Objects.equal(serviceName, that.serviceName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(instanceID, host, port, serviceName);
    }
}
