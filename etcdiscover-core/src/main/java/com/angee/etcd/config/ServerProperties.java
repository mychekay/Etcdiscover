package com.angee.etcd.config;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Getter
@Setter
public class ServerProperties {
    /**
     * endpoints config
     */
    private String[] endpoints = {"http://localhost:2379"};

    /**
     * your etcd username
     */
    private String username;

    /**
     * your etcd password
     */
    private String password;

    /**
     * etcd client connection manager config
     */
    private ConnectionManager connectionPool = new ConnectionManager();

    public static class ConnectionManager {
        /**
         * fixed thread pool max size
         */
        private int poolMaxSize = 100;

        /**
         * idle thread alive time
         */
        private long keepAliveTimeMillis = 60 * 1000;

        /**
         * threadpool task blocking queue size
         */
        private int blockingCapacity = 100;

        public int getPoolMaxSize() {
            return poolMaxSize;
        }

        public void setPoolMaxSize(int poolMaxSize) {
            this.poolMaxSize = poolMaxSize;
        }

        public long getKeepAliveTimeMillis() {
            return keepAliveTimeMillis;
        }

        public void setKeepAliveTimeMillis(long keepAliveTimeMillis) {
            this.keepAliveTimeMillis = keepAliveTimeMillis;
        }

        public int getBlockingCapacity() {
            return blockingCapacity;
        }

        public void setBlockingCapacity(int blockingCapacity) {
            this.blockingCapacity = blockingCapacity;
        }
    }
}
