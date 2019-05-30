package com.zhangmen.etcd.balanced.algorithm;

import com.zhangmen.etcd.bean.AbstractInstance;
import com.zhangmen.etcd.bean.Instance;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.concurrent.atomic.LongAdder;

/**
 * Copyright© 2019
 * Author jie.han
 * Created on 2019-05-30
 */
@ThreadSafe
public class Poll implements BalancedAlgorithm {
    private static Poll ourInstance = new Poll();

    public static Poll getInstance() {
        return ourInstance;
    }

    private Poll() {
    }

    @Override
    public Instance apply(String serviceName, Collection<AbstractInstance> instances) {
        return null;
    }

    private class Mark {
        private LongAdder mark = new LongAdder();

        public long value() {
            return mark.longValue();
        }

        public int intValue() {
            return mark.intValue();
        }

        public void increment() {
            mark.increment();
            if (mark.longValue() >= Integer.MAX_VALUE)
                mark.reset();
        }

        public void reset() {
            mark.reset();
        }
    }
}
