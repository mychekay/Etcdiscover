package com.angee.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "etcd.http.pool")
public class RestClientProperties {
    private int maxIdle = 5;
    private long keepAliveMin = 5;
    private long timeoutMillis = 1000;
    private long readTimeoutSec = 0;
    private long writeTimeoutSec = 0;
    private boolean retry = true;
}