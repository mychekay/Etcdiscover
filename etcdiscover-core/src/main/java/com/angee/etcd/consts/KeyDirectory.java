package com.angee.etcd.consts;

import java.util.StringJoiner;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public class KeyDirectory {
    public static final String DELIMITER = "/";

    public static class First {
        public static final String SERVICE = "service";
    }

    public static String buildDir(String... keys) {
        StringJoiner joiner = new StringJoiner(DELIMITER, "", DELIMITER);
        for (String key : keys) {
            joiner.add(key);
        }
        return joiner.toString();
    }
}
