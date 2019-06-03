package com.angee.autoconfigure;

import com.angee.autoconfigure.properties.RestClientProperties;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Configuration
@Import(RestClientProperties.class)
public class RestClientConfig {

    @Bean
    public OkHttpClient okHttpClient(RestClientProperties properties) {
        return new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(properties.getMaxIdle(), properties.getKeepAliveMin(), TimeUnit.MINUTES))
                .connectTimeout(properties.getTimeoutMillis(), TimeUnit.MILLISECONDS)
                .readTimeout(properties.getReadTimeoutSec(), TimeUnit.SECONDS)
                .writeTimeout(properties.getWriteTimeoutSec(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(properties.isRetry())
                .build();
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(OkHttpClient okHttpClient) {
        return new OkHttp3ClientHttpRequestFactory(okHttpClient);
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        return new RestTemplate(clientHttpRequestFactory);
    }

}
