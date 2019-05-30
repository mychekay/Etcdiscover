package com.zhangmen.etcd;

import java.lang.annotation.*;

/**
 * code is far away from bug with the animal protecting
 *  ┏┓   ┏┓
 *┏┛┻━━━┛┻┓
 *┃       ┃
 *┃   ━   ┃
 *┃ ┳┛ ┗┳ ┃
 *┃       ┃
 *┃   ┻   ┃
 *┃       ┃
 *┗━┓   ┏━┛
 *  ┃   ┃神兽保佑
 *  ┃   ┃代码无BUG！
 *  ┃   ┗━━━┓
 *  ┃       ┣┓
 *  ┃       ┏┛
 *  ┗┓┓┏━┳┓┏┛
 *   ┃┫┫ ┃┫┫
 *   ┗┻┛ ┗┻┛
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoBug {
}
