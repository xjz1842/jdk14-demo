package com.jdk9.demo.collection;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectTest {


    public static void main(String[] args) {
        //list
       List list = List.of("a","b","c");
       list.stream().forEach(k-> System.out.println(k));

       //set
        Set set  = Set.of("a","b","c");
        set.forEach(k-> System.out.println(k));

        //map
        Map  map = Map.of(1,"a",2,"b",3,"c");
        map.forEach((k,v)-> System.out.println(k+"---" +v));

    }
}
