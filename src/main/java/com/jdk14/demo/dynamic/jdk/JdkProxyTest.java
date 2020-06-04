package com.jdk14.demo.dynamic.jdk;

import com.jdk14.demo.dynamic.myjdk.Man;
import com.jdk14.demo.dynamic.myjdk.Zhangsan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTest {

    public static void main(String[] args) throws Throwable{
         Method method = Class.forName("com.jdk14.demo.dynamic.myjdk.Zhangsan").getMethod("findObject");

         Zhangsan zhangsan = new Zhangsan();

        //1.Constructor
         for(int i=0; i < 16; i++) {
             method.invoke(zhangsan);
         }

        System.setProperty("dk.proxy.ProxyGenerator.saveGeneratedFiles","true");

        //2.Method
        Man man = new Zhangsan();
        InvocationHandler handler = new JdkHandler(man);
        Man man1 = (Man)Proxy.newProxyInstance(JdkProxyTest.class.getClassLoader(),new Class[]{Man.class},handler);
        man1.findObject();

        //3.Method
        for(int i=0; i <=16; i++) {
            if(16 == i) {
                Class.forName("com.jdk14.demo.dynamic.myjdk.Zhangsan").getField("test");
            }
        }
    }



}
