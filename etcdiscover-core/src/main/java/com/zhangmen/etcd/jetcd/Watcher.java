package com.zhangmen.etcd.jetcd;

import io.etcd.jetcd.Watch;
import io.etcd.jetcd.options.WatchOption;

import static com.zhangmen.etcd.util.ByteSequenceUtil.fromString;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
public class Watcher {
    private Watch watch;

    public Watcher(Watch watch) {
        this.watch = watch;
    }

    public void watchAll(String prefix, Watch.Listener listener) {
        WatchOption watchOption = WatchOption.newBuilder()
                .withRange(fromString("\0"))
                .withPrefix(fromString(prefix))
                .build();
        this.watch.watch(fromString("\0"), watchOption, listener);
    }

}
