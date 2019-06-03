package com.angee.autoconfigure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "etcd.okhttp.pool")
public class RestClientProperties {
    /**
     * max idle connections
     */
    private int maxIdle = 5;

    /**
     * keep alive minutes
     */
    private long keepAliveMin = 5;

    /**
     * timeout millis
     */
    private long timeoutMillis = 1000;

    /**
     * read timeout seconds
     */
    private long readTimeoutSec = 0;

    /**
     * write timeout seconds
     */
    private long writeTimeoutSec = 0;

    /**
     * retry boolean
     */
    private boolean retry = true;
}