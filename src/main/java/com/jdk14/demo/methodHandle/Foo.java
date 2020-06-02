package com.jdk14.demo.methodHandle;

import java.io.StringWriter;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.lang.invoke.MethodType.methodType;

public class Foo {

    private static void bar(Object o) {
        new Exception().printStackTrace();
    }

    public static MethodHandles.Lookup lookup() {
        return MethodHandles.lookup();
    }

    public static void main(String[] args) throws Throwable {
        // 获取方法句柄的不同方式
        MethodHandles.Lookup l = Foo.lookup();
        // 具备Foo类的访问权限
        Method m = Foo.class.getDeclaredMethod("bar", Object.class);

        MethodHandle mh0 = l.unreflect(m);

        mh0.invokeExact((Object) "a");

        MethodType t = methodType(void.class, Object.class);

        MethodHandle mh1 = l.findStatic(Foo.class, "bar", t);

        //BindTo
        StringWriter swr = new StringWriter();
        MethodHandle swWrite = MethodHandles.lookup().findVirtual(StringWriter.class, "write", methodType(void.class, char[].class, int.class, int.class)).bindTo(swr);
        MethodHandle swWrite4 = swWrite.asCollector(0, char[].class, 4);
        swWrite4.invoke('A', 'B', 'C', 'D', 1, 2);
//        assertEquals("BC", swr.toString());
        swWrite4.invoke('P', 'Q', 'R', 'S', 0, 4);
//        assertEquals("BCPQRS", swr.toString());
        swWrite4.invoke('W', 'X', 'Y', 'Z', 3, 1);
//        assertEquals("BCPQRSZ", swr.toString());

        //测试
        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            mh1.invokeExact(new Object());
        }


    }


}
