package com.angee.etcd.util.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
public class ThreadPoolUtil {
    public static ExecutorService fifoFixedPool(int nThread, long keepAliveTime, int blockingCapacity) {
        return new ThreadPoolExecutor(
                nThread,
                nThread,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(blockingCapacity),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public static ExecutorService discardFixedPool(int nThread, long keepAliveTime, int blockingCapacity) {
        return new ThreadPoolExecutor(
                nThread,
                nThread,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(blockingCapacity),
                new ThreadPoolExecutor.DiscardPolicy());
    }

    public static ExecutorService nonRejectFixedPool(int nThread, long keepAliveTime, int blockingCapacity) {
        return new ThreadPoolExecutor(
                nThread,
                nThread,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(blockingCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
