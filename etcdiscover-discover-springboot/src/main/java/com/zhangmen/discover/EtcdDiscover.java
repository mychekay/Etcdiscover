package com.zhangmen.discover;

import com.zhangmen.etcd.balanced.BalancedStrategy;
import com.zhangmen.etcd.bean.Instance;
import com.zhangmen.etcd.exception.NotFoundServiceException;
import com.zhangmen.etcd.core.Discovery;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Component
public class EtcdDiscover<T, R> {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Discovery discovery;

    public ResponseEntity<R> postMinimal(String serviceName, String uri, T t, Class<R> responseTyp) throws NotFoundServiceException {
        return post(serviceName, uri, t, responseTyp, BalancedStrategy.MIN);
    }

    public ResponseEntity<R> post(String serviceName, String uri, T t, Class<R> responseType, BalancedStrategy balancedStrategy) throws NotFoundServiceException {
        Instance instance = discovery.discover(serviceName, balancedStrategy);
        if (instance == null) {
            throw new NotFoundServiceException("not found service " + serviceName);
        }
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
