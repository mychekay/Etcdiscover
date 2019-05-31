package com.angee.annotation;

import com.angee.autoconfigure.RegisterAutoConfiguration;
import com.angee.etcd.anntotation.NoBug;
import com.angee.starter.Bootstrap;
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
@Import({RegisterAutoConfiguration.class, Bootstrap.class})
public @interface EnableEtcdClient {
    String serviceName() default "";
}
