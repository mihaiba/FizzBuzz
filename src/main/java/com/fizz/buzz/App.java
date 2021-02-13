package com.fizz.buzz;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        //mapper example
        FizzBuzz service = new FizzBuzz();
        String result = IntStream.rangeClosed(1, 20).mapToObj(service::map).collect(Collectors.joining(" "));
        System.out.println(result);

        //report example
        FizzBuzzReporter report = new FizzBuzzReporter(new FizzBuzz());
        System.out.println(report.generateReport(IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList())));
    }
}
