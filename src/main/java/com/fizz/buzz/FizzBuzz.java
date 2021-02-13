package com.fizz.buzz;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz {
    private static final String FIZZ = "fizz";
    private static final String BUZZ = "buzz";

    public String map(int input) {
        String collector = "";
        if (input % 3 == 0) {
            collector = FIZZ;
        }
        if (input % 5 == 0) {
            collector += BUZZ;
        }
        return collector.isEmpty() ? String.valueOf(input) : collector;
    }

    public static void main(String[] args) {
        FizzBuzz service = new FizzBuzz();
        String result = IntStream.rangeClosed(1, 20).mapToObj(service::map).collect(Collectors.joining(" "));
        System.out.println(result);
    }
}
