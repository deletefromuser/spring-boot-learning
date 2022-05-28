package com.example.springboot;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.google.common.util.concurrent.Uninterruptibles;

import org.junit.jupiter.api.Test;

public class FunctionInterfaceTest {

    @Test
    void testFunctionInterface() {
        int[] fibs = { 0, 1 };
        Stream<Integer> fibonacci = Stream.generate(() -> {
            int result = fibs[1];
            int fib3 = fibs[0] + fibs[1];
            fibs[0] = fibs[1];
            fibs[1] = fib3;
            return result;
        });

        fibonacci.limit(10).forEach(System.out::println);
    }

    double squareLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 2);
    }

    @Test
    void testSupplier() {
        Map<String, Integer> salaries = new HashMap<>();
        salaries.put("John", 40000);
        salaries.put("Freddy", 30000);
        salaries.put("Samuel", 50000);

        salaries.replaceAll((name, oldValue) -> name.equals("Freddy") ? oldValue : oldValue + 10000);
        System.out.println(salaries);

        Supplier<Double> lazyValue = () -> {
            Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);
            return 9d;
        };

        System.out.println(LocalDateTime.now());
        Double valueSquared = squareLazy(lazyValue);
        System.out.println(valueSquared);
        System.out.println(LocalDateTime.now());

        System.out.println(salaries);

    }

    @Test
    void testOperator() {
        List<Integer> values = Arrays.asList(3, 5, 8, 9, 12);

        int sum = values.stream()
          .reduce(0, (i1, i2) -> i1 + i2);

          System.out.println(sum);
    }

}
