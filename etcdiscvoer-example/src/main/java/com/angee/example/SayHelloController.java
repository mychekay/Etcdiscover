package com.angee.example;

import com.angee.discover.EtcdDiscover;
import com.angee.etcd.balanced.BalancedStrategy;
import com.angee.etcd.exception.NotFoundServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-31
 */
@RestController
public class SayHelloController {
    @Autowired
    private EtcdDiscover etcdDiscover;

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello() throws NotFoundServiceException {
        return etcdDiscover.post("etcdiscover-example", "/hello", null, String.class, BalancedStrategy.RANDOM);
    }

}
