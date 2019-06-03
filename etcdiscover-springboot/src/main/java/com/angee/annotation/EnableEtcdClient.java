package com.angee.annotation;

import com.angee.EtcdDiscover;
import com.angee.EtcdProvider;
import com.angee.autoconfigure.ClientAutoConfiguration;
import com.angee.autoconfigure.RestClientConfig;
import com.angee.etcd.anntotation.NoBug;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-29
 */
@NoBug
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import({ClientAutoConfiguration.class, RestClientConfig.class, EtcdDiscover.class, EtcdProvider.class})
public @interface EnableEtcdClient {
    String serviceName() default "";
}
