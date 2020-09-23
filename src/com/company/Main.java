package com.company;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        List<Integer>a = new ArrayList<Integer>();//List<Integer>();
        a.add(1);
        a.add(11);
        a.add(111);
        a.add(11111);
        a.add(111111);

        // Adding list elements

        AtomicReference<Integer> sum = new AtomicReference<>(0);

        a.forEach(i -> sum.updateAndGet(v -> v + i));

        System.out.println("sum comes out ot be"+ sum);

        // Simple method to iterate list
        a.forEach(System.out::println);
        a.forEach(s -> System.out.println(s));

        // Using java 8 consumer Interface to iterate list.
        Consumer<Integer> consume = s -> {System.out.println(s);};
//          a.forEach(consume);
        a.forEach(System.out::println);

        Map<String, Integer> mp = new HashMap<>();
        mp.put("Rohit", 10);
        mp.put("a", 10);
        mp.put("b", 10);
        mp.put(null, 10);

        mp.entrySet().forEach((k) -> System.out.println("Entered:" + k ));
        
        mp.entrySet().stream().forEach((k) -> System.out.println("Entered in stream " + k ));


        // Functional Programming

        // Lambda Expression

        // Stream API




    }
}
