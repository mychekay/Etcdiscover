package com.angee.etcd.jetcd;

import com.angee.etcd.bean.AbstractInstance;
import com.angee.etcd.consts.ServiceKeyPrefix;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.kv.PutResponse;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.options.PutOption;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.angee.etcd.util.ByteSequenceUtil.fromString;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Slf4j
public class KVer<T extends AbstractInstance> {
    private Class<T> tClass;

    private KV kv;

    public KVer(KV kv) {
        this.kv = kv;
        Type t = getClass().getGenericSuperclass();
//        this.tClass = (Class<T>)((ParameterizedType)t);
    }

    public void put(String key, T instance) throws ExecutionException, InterruptedException {
        if (key == null || instance == null) {
            throw new NullPointerException("null key or weight");
        }
        PutOption putOption = PutOption.newBuilder()
                .withPrevKV()
                .build();
        kv.put(fromString(key), fromString(instance.toString()), putOption).get();
    }

    public void put(String key, T instance, long leaseID) throws ExecutionException, InterruptedException {
        if (key == null || instance == null) {
            throw new NullPointerException("null key or weight");
        }
        PutOption putOption = PutOption.newBuilder()
                .withLeaseId(leaseID)
                .withPrevKV()
                .build();
        PutResponse putResponse = kv.put(fromString(key), fromString(instance.toString()), putOption).get();
        log.info(putResponse.toString());
    }

    public void delete(String key) throws ExecutionException, InterruptedException {
        if (key == null) {
            throw new NullPointerException("null key");
        }
        kv.delete(fromString(key)).get();
    }

    public Set<T> getAll() throws ExecutionException, InterruptedException {
        ByteSequence key = fromString("\0");
        GetOption getOption = GetOption.newBuilder()
                .withRange(fromString("\0"))
                .withPrefix(fromString(ServiceKeyPrefix.prefix))
                .build();
        CompletableFuture<GetResponse> future = kv.get(key, getOption);
        List<KeyValue> keyValueList = future.get().getKvs();
        if (CollectionUtils.isEmpty(keyValueList)) return null;
        else {
            Set<T> instances = new HashSet<>();
            for (KeyValue keyValue : keyValueList) {
                T instance = AbstractInstance.fromByteSequence(keyValue, tClass);
                if (instance == null) continue;
                instances.add(instance);
            }
            return instances;
        }
    }

}
