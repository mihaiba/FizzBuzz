package com.fizz.buzz;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        FizzBuzz service = new FizzBuzz();
        String result = IntStream.rangeClosed(1, 20).mapToObj(service::map).collect(Collectors.joining(" "));
        System.out.println(result);
    }
}
