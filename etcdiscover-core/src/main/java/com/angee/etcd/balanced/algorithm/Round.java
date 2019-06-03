package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.core.instance.AbstractInstance;

import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
@ThreadSafe
public class Round implements BalancedAlgorithm {
    private static Round ourInstance = new Round();

    public static Round getInstance() {
        return ourInstance;
    }

    private Mark mark;

    private Round() {
        this.mark = new Mark(Integer.MAX_VALUE >> 16);
    }

    @Override
    public AbstractInstance apply(Object reqParams, List<AbstractInstance> instances) {
        return instances.get(mark.getIntValAndIncrement() % instances.size());
    }

    private class Mark {
        private LongAdder mark;
        private long bound;

        Mark(int start) {
            this(start, Integer.MAX_VALUE);
        }

        Mark(int start, long bound) {
            this.mark = new LongAdder();
            mark.add(start);
            this.bound = bound;
        }

        int intValue() {
            return mark.intValue();
        }

        void increment() {
            mark.increment();
            if (mark.longValue() >= bound)
                mark.reset();
        }

        synchronized int getIntValAndIncrement() {
            int ret = intValue();
            increment();
            return ret;
        }

    }
}
