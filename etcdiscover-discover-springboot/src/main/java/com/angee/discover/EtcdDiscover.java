package com.angee.discover;

import com.angee.etcd.balanced.BalancedStrategy;
import com.angee.etcd.balanced.algorithm.BalancedAlgorithm;
import com.angee.etcd.bean.Instance;
import com.angee.etcd.core.Discovery;
import com.angee.etcd.exception.NotFoundServiceException;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public class EtcdDiscover<T, R> {
    private final RestTemplate restTemplate;
    private final Discovery<Instance> discovery;

    public EtcdDiscover(RestTemplate restTemplate, Discovery<Instance> discovery) {
        this.restTemplate = restTemplate;
        this.discovery = discovery;
    }

    public ResponseEntity<R> post(String serviceName, String uri, T t, Class<R> responseType) throws NotFoundServiceException {
        Instance instance = discovery.discover(serviceName, BalancedStrategy.ROUND);
        if (instance == null)
            throw new NotFoundServiceException("not found service " + serviceName);
        String url = Protocol.HTTP.getHead() + instance.getHost() + ":" + instance.getPort() + uri;
        return restTemplate.postForEntity(url, t, responseType);
    }

    public ResponseEntity<R> post(String serviceName, String uri, T t, Class<R> responseType, BalancedStrategy balancedStrategy) throws NotFoundServiceException {
        Instance instance = discovery.discover(serviceName, balancedStrategy);
        if (instance == null)
            throw new NotFoundServiceException("not found service " + serviceName);
        String url = Protocol.HTTP.getHead() + instance.getHost() + ":" + instance.getPort() + uri;
        return restTemplate.postForEntity(url, t, responseType);
    }

    public ResponseEntity<R> post(String serviceName, String uri, T t, Class<R> responseType, BalancedAlgorithm balancedAlgorithm) throws NotFoundServiceException {
        Instance instance = discovery.discover(serviceName, balancedAlgorithm);
        if (instance == null)
            throw new NotFoundServiceException("not found service " + serviceName);
        String url = Protocol.HTTP.getHead() + instance.getHost() + ":" + instance.getPort() + uri;
        return restTemplate.postForEntity(url, t, responseType);
    }

    private enum Protocol {
        HTTP("http://"), HTTPS("https://");

        @Getter
        private String head;

        Protocol(String head) {
            this.head = head;
        }
    }
}
