package com.angee.etcd.exception;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
public class DeserializeException extends Exception {
    public DeserializeException(String message) {
        super(message);
    }

    public DeserializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
