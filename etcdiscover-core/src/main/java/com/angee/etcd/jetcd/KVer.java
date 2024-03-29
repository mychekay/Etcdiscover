package com.angee.etcd.jetcd;

import com.angee.etcd.consts.KeyDirectory;
import com.angee.etcd.core.instance.AbstractInstance;
import com.angee.etcd.exception.DeserializeException;
import com.angee.etcd.util.serialize.SerializerFactory;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.kv.PutResponse;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.options.PutOption;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.angee.etcd.util.ByteSequenceUtil.fromString;
import static com.angee.etcd.util.serialize.Scheme.FAST_JSON;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Slf4j
public class KVer<T extends AbstractInstance> {
    private KV kv;

    public KVer(KV kv) {
        this.kv = kv;
    }

    public void put(String key, T instance) throws Exception {
        if (key == null || instance == null) {
            throw new NullPointerException("null key or weight");
        }
        PutOption putOption = PutOption.newBuilder()
                .withPrevKV()
                .build();
        ByteSequence sequenceKey = fromString(key);
        ByteSequence sequenceVal = fromString(SerializerFactory.create(FAST_JSON).serialize(instance));
        PutResponse putResponse = kv.put(sequenceKey, sequenceVal, putOption).get();
        log.info(putResponse.toString());
    }

    public void put(String key, T instance, long leaseID) throws Exception {
        if (key == null || instance == null) {
            throw new NullPointerException("null key or weight");
        }
        PutOption putOption = PutOption.newBuilder()
                .withLeaseId(leaseID)
                .withPrevKV()
                .build();
        ByteSequence sequenceKey = fromString(key);
        ByteSequence sequenceVal = fromString(SerializerFactory.create(FAST_JSON).serialize(instance));
        PutResponse putResponse = kv.put(sequenceKey, sequenceVal, putOption).get();
        log.info(putResponse.toString());
    }

    public void delete(String key) throws ExecutionException, InterruptedException {
        if (key == null) {
            throw new NullPointerException("null key");
        }
        kv.delete(fromString(key)).get();
    }

    public T get(String key, Class<T> targetClass) {
        ByteSequence sequenceKey = fromString(key);
        GetOption getOption = GetOption.newBuilder().withLimit(1).build();
        try {
            GetResponse getResponse = kv.get(sequenceKey, getOption).get();
            List<KeyValue> keyValueList = getResponse.getKvs();
            if (CollectionUtils.isEmpty(keyValueList)) return null;
            return SerializerFactory.create(FAST_JSON).deserialize(keyValueList.get(0).getValue().getBytes(), targetClass);
        } catch (InterruptedException | ExecutionException | DeserializeException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public Set<T> getAll(Class<T> targetClass) {
        ByteSequence key = fromString("\0");
        GetOption getOption = GetOption.newBuilder()
                .withRange(fromString("\0"))
                .withPrefix(fromString(KeyDirectory.First.SERVICE))
                .build();
        CompletableFuture<GetResponse> future = kv.get(key, getOption);
        GetResponse getResponse = null;
        try {
            getResponse = future.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        if (null == getResponse) return null;
        List<KeyValue> keyValueList = getResponse.getKvs();
        if (CollectionUtils.isEmpty(keyValueList)) return null;
        else {
            Set<T> instances = new HashSet<>();
            for (KeyValue keyValue : keyValueList) {
                T instance = null;
                try {
                    instance = SerializerFactory.create(FAST_JSON).deserialize(keyValue.getValue().getBytes(), targetClass);
                } catch (DeserializeException e) {
                    log.error(e.getMessage(), e);
                    continue;
                }
                if (instance == null) continue;
                instances.add(instance);
            }
            return instances;
        }
    }

}
