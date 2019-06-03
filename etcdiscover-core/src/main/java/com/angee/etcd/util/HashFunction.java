package com.angee.etcd.util;

import com.angee.etcd.exception.NoSuchAlgorithmException;

import java.security.MessageDigest;

public class HashFunction {

    public static long hash(String key) throws NoSuchAlgorithmException {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException(e.getMessage(), e.getCause());
        }
        md5.reset();
        md5.update(key.getBytes());
        byte[] bKey = md5.digest();
        long result = ((long) (bKey[3] & 0xFF) << 24)
                | ((long) (bKey[2] & 0xFF) << 16
                | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF));
        return result & 0xffffffffL;
    }

}