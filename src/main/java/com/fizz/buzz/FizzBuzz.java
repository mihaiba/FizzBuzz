package com.fizz.buzz;

public class FizzBuzz {
    private static final String FIZZ = "fizz";
    private static final String BUZZ = "buzz";
    private static final String THREE = "3";
    private static final String ALFRESCO = "alfresco";

    public String map(int input) {
        String collector = "";
        if (input % 3 == 0) {
            collector = FIZZ;
        }
        if (String.valueOf(input).contains(THREE)) {
            collector = ALFRESCO;
        }
        if (input % 5 == 0) {
            collector += BUZZ;
        }
        return collector.isEmpty() ? String.valueOf(input) : collector;
    }
}
