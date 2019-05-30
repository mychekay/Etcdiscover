package com.angee.etcd.jetcd;

import io.etcd.jetcd.Lease;
import io.etcd.jetcd.lease.LeaseGrantResponse;
import io.etcd.jetcd.lease.LeaseKeepAliveResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public class Leaser {
    private final Lease lease;

    public Leaser(Lease lease) {
        this.lease = lease;
    }

    public long grant(long ttl) throws ExecutionException, InterruptedException {
        CompletableFuture<LeaseGrantResponse> future = lease.grant(ttl);
        return future.get().getID();
    }

    public long keepAliveOnce(long leaseID) throws ExecutionException, InterruptedException {
        CompletableFuture<LeaseKeepAliveResponse> future = lease.keepAliveOnce(leaseID);
        return future.get().getID();
    }

    public void revoke(long leaseID) {
        lease.revoke(leaseID);
    }
}
