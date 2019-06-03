package com.angee.etcd.util.serialize;

import com.angee.etcd.exception.DeserializeException;
import com.angee.etcd.exception.SerializeException;
import lombok.NonNull;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
public abstract class AbstractSerializer {
    /**
     * 序列化
     * @param source
     * @return
     */
    public abstract String serialize(@NonNull Object source) throws SerializeException;

    /**
     * 反序列化
     * @param source
     * @param type
     * @return
     */
    public abstract <T> T deserialize(String source, Class<T> type) throws DeserializeException;

    /**
     * 反序列化byte[]
     * @param source
     * @param type
     * @param <T>
     * @return
     * @throws DeserializeException
     */
    public abstract <T> T deserialize(byte[] source, Class<T> type) throws DeserializeException;
}
