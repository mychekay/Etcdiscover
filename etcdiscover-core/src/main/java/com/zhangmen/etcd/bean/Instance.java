package com.zhangmen.etcd.bean;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.etcd.jetcd.KeyValue;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Slf4j
@Data
public class Instance extends AbstractInstance implements Comparable<String> {
    private String weight;

    @Override
    public int compareTo(String weight) {
        if (StringUtils.isEmpty(weight) && StringUtils.isEmpty(this.weight)) return 0;
        else if (StringUtils.isEmpty(weight)) return -1;
        else if (StringUtils.isEmpty(this.weight)) return 1;
        else return this.weight.compareTo(weight);
    }

    public static Instance fromByteSequence(KeyValue keyValue) {
        try {
            return JSONObject.parseObject(keyValue.getValue().getBytes(), Instance.class);
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
