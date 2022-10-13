package com.test.effectivejava;

import java.util.ArrayList;
import java.util.List;

public class Main3 {

    public static void main(String[] args) {
        // Prefer for-each loops to traditional for loops
        List<Integer> integers = new ArrayList<>(List.of(1,2,3,4,5));
        // NEE !!!
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }

        // OK ^_^
        for (Integer integer : integers) {
            System.out.println(integer);
        }

        // OK ^_^
        integers.forEach(System.out::println);
    }

}
