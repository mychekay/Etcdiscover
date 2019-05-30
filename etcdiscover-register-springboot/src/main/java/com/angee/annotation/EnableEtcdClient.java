package com.angee.annotation;

import com.angee.autoconfigure.LeasePropertiesBean;
import com.angee.autoconfigure.RegisterAutoConfiguration;
import com.angee.autoconfigure.ServerPropertiesBean;
import com.angee.register.Bootstrap;
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
@Import({ServerPropertiesBean.class, LeasePropertiesBean.class, RegisterAutoConfiguration.class, Bootstrap.class})
public @interface EnableEtcdClient {
    String serviceName() default "";
}
