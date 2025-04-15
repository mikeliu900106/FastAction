package org.example.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  // 設置為運行時可用
@Target(ElementType.FIELD)
public @interface CheckValue {
    String type() default "String";
    boolean require() default true;
}
