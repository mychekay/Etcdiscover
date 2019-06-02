package com.angee.etcd.exception;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-06-03
 */
public class SerializeException extends Exception {
    public SerializeException(String message) {
        super(message);
    }

    public SerializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
