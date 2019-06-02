package com.angee.etcd.balanced.algorithm;

import com.angee.etcd.bean.AbstractInstance;

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

        public Mark(int start) {
            this(start, Integer.MAX_VALUE);
        }

        public Mark(int start, long bound) {
            this.mark = new LongAdder();
            mark.add(start);
            this.bound = bound;
        }

        public int intValue() {
            return mark.intValue();
        }

        public void increment() {
            mark.increment();
            if (mark.longValue() >= bound)
                mark.reset();
        }

        public synchronized int getIntValAndIncrement() {
            int ret = intValue();
            increment();
            return ret;
        }

    }
}
