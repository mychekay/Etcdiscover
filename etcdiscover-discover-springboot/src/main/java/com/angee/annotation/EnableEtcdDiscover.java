package com.angee.annotation;

import com.angee.autoconfigure.DiscoverAutoConfiguration;
import com.angee.autoconfigure.RestClientConfig;
import com.angee.discover.EtcdDiscover;
import com.angee.etcd.anntotation.NoBug;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
@NoBug
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import({DiscoverAutoConfiguration.class, RestClientConfig.class, EtcdDiscover.class})
public @interface EnableEtcdDiscover {
}
