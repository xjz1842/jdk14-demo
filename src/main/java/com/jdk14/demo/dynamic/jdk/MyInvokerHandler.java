package com.jdk14.demo.dynamic.jdk;

import java.lang.reflect.Method;

public interface MyInvokerHandler {

    Object invoke(Object proxy,Method method,Object[] args)throws Throwable;

}