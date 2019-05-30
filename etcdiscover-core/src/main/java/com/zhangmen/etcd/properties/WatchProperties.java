package com.zhangmen.etcd.properties;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.options.WatchOption;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-28
 */
@Getter
@Setter
@Slf4j
public class WatchProperties {
    private long revision = 0L;
    private Optional<ByteSequence> endKey = Optional.empty();
    private boolean prevKV = false;
    private boolean progressNotify = false;
    private boolean noPut = false;
    private boolean noDelete = false;

    public WatchOption buildWatchOption() {
        boolean noPut = this.isNoPut();
        boolean noDelete = this.isNoDelete();
        boolean processNotify = this.isProgressNotify();
        boolean prevKV = this.isPrevKV();
//        ByteSequence range = this.getEndKey().get();
        long revision = this.getRevision();
        return WatchOption.newBuilder()
                .withNoPut(noPut)
                .withNoDelete(noDelete)
                .withProgressNotify(processNotify)
                .withPrevKV(prevKV)
//                .withRange(range)
                .withRevision(revision)
                .build();
    }

}
