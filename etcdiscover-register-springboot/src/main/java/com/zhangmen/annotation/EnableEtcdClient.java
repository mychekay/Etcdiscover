package com.zhangmen.annotation;

import com.zhangmen.register.Bootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-29
 */
@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(Bootstrap.class)
public @interface EnableEtcdClient {
    String serviceName() default "";
}
