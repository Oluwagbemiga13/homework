package com.test.effectivejava.practise;

import java.util.ArrayList;
import java.util.List;

public class LambdaExpresions {




    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(List.of(1, 5, 3, 28, 19));

        list.forEach(x -> System.out.println(x));

        list.sort(Integer::compareTo);

        list.forEach(System.out::println);


    }
}
