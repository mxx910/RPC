package com.mxx910.rpc.annotation;

import java.lang.annotation.*;

/**
 * @author: mxx910
 * @date: 2023/6/27
 * @description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface RpcReference {

    /**
     * Service version, default value is empty string
     */
    String version() default "";

    /**
     * Service group, default value is empty string
     */
    String group() default "";

}

