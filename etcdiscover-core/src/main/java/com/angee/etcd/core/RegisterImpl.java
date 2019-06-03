package com.angee.etcd.core;

import com.angee.etcd.anntotation.NoBug;
import com.angee.etcd.core.instance.Instance;
import com.angee.etcd.consts.KeyDirectory;
import com.angee.etcd.jetcd.KVer;
import com.angee.etcd.jetcd.Leaser;
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
@NoBug
public class RegisterImpl implements Register<Instance> {
    private KVer<Instance> kVer;
    private Leaser leaser;
    private long ttl;

    public RegisterImpl(KVer<Instance> kVer, Leaser leaser, long ttl) {
        this.kVer = kVer;
        this.leaser = leaser;
        this.ttl = ttl;
    }

    @Override
    public boolean register(String serviceName, Instance instance) {
        try {
            long leaseID = leaser.grant(ttl);
            kVer.put(KeyDirectory.First.SERVICE + serviceName, instance, leaseID);
            this.keepLease(leaseID);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean update(String serviceName, Instance instance) {
        try {
            kVer.put(KeyDirectory.First.SERVICE + serviceName, instance);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean unReg(String serviceName, Instance instance) {
        try {
            kVer.delete(KeyDirectory.First.SERVICE + serviceName);
            return true;
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    private boolean keepLease(long leaseID) {
        //续租
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    leaser.keepAliveOnce(leaseID);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }, ttl * 1000 / 2, ttl * 1000 / 2);
        return true;
    }

}
