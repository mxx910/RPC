package com.mxx910.rpc.annotation;

import java.lang.annotation.*;

/**
 * @author: mxx910
 * @date: 2023/6/27
 * @description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface RpcService {

    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";

}
