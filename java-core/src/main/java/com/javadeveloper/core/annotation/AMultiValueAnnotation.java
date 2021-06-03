package com.javadeveloper.core.annotation;

public @interface AMultiValueAnnotation {
    int value() default 0;
    String strValue() default "Hello From Other Side";
}
