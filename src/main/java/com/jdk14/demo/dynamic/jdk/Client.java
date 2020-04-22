package com.jdk14.demo.dynamic.jdk;

public class Client {

    public static void main(String[] args) throws Throwable{
        Man man = new Zhangsan();

        MyHandler myHandler = new MyHandler(man);

        Man proxyMan = (Man)MyProxy.newProxyInstance(new MyClassLoader("/Users/zxj/github/jdk14-demo/src/main/java/com/jdk14/demo/dynamic/jdk","com.jdk14.demo.dynamic.jdk"),Man.class,myHandler);

        System.out.println(proxyMan.getClass().getName());

        proxyMan.findObject();
    }


}
