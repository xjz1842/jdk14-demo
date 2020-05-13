package com.jdk14.demo.dynamic.jdk;

import com.jdk14.demo.dynamic.myjdk.Man;
import com.jdk14.demo.dynamic.myjdk.Zhangsan;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestMain {

    public static void main(String[] args) throws Throwable{

        //1.Constructor
         for(int i=0; i < 16; i++) {
             if(14 == i) {
                 Class.forName("com.jdk14.demo.dynamic.myjdk.Zhangsan").getConstructor().newInstance();
             }else{
                 Class.forName("com.jdk14.demo.dynamic.myjdk.Zhangsan").getConstructor().newInstance();
             }
         }

        //2.Method
        Man man = new Zhangsan();
        InvocationHandler handler = new JdkHandler(man);
        Man man1 = (Man)Proxy.newProxyInstance(TestMain.class.getClassLoader(),new Class[]{Man.class},handler);
        man1.findObject();

        //3.Method
        for(int i=0; i <=16; i++) {
            if(16 == i) {
                Class.forName("com.jdk14.demo.dynamic.myjdk.Zhangsan").getField("test");
            }
        }
    }
}
