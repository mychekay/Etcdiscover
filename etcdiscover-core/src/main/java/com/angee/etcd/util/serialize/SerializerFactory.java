package com.angee.etcd.util.serialize;

import com.angee.etcd.util.serialize.impl.FastjsonSerializer;
import com.angee.etcd.util.serialize.impl.GsonSerializer;
import com.angee.etcd.util.serialize.impl.JacksonSerializer;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-31
 */
public class SerializerFactory {

    public SerializerFactory() {
    }

    public static AbstractSerializer create(Scheme scheme) {
        switch (scheme) {
            case FAST_JSON:
                return FastjsonSerializer.getInstance();
            case GSON:
                return GsonSerializer.getInstance();
            case JACKSON:
            default:
                return JacksonSerializer.getInstance();
        }
    }
}
