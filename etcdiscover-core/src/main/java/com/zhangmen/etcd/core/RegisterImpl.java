package com.zhangmen.etcd.core;

import com.zhangmen.etcd.bean.AbstractInstance;
import com.zhangmen.etcd.consts.ServiceKeyPrefix;
import com.zhangmen.etcd.jetcd.KVer;
import com.zhangmen.etcd.jetcd.Leaser;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Slf4j
public class RegisterImpl implements Register {
    private KVer kVer;
    private Leaser leaser;
    private long ttl;

    public RegisterImpl(KVer kVer, Leaser leaser, long ttl) {
        this.kVer = kVer;
        this.leaser = leaser;
        this.ttl = ttl;
    }

    @Override
    public boolean register(String serviceName, AbstractInstance instance) {
        try {
            instance.setServiceName(serviceName);
            long leaseID = leaser.grant(ttl);
            kVer.put(ServiceKeyPrefix.prefix + serviceName, instance, leaseID);
            //续租
            Timer timer = new Timer(true);
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        leaser.keepAliveOnce(leaseID);
                    } catch (ExecutionException | InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                }
            }, ttl * 1000 / 2, ttl * 1000 / 2);
            return true;
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean update(String serviceName, AbstractInstance instance) {
        try {
            instance.setServiceName(serviceName);
            kVer.put(ServiceKeyPrefix.prefix + serviceName, instance);
            return true;
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean unReg(String serviceName, AbstractInstance instance) {
        try {
            kVer.delete(ServiceKeyPrefix.prefix + serviceName);
            return true;
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

}
