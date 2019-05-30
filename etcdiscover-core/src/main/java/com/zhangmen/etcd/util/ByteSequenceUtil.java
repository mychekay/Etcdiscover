package com.zhangmen.etcd.util;

import io.etcd.jetcd.ByteSequence;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public class ByteSequenceUtil {
    public static ByteSequence fromString(String source) {
        if (null == source)
            return null;
        return ByteSequence.from(source.getBytes());
    }
}
