package com.service.number.fibonacci;

import org.springframework.stereotype.Component;

@Component
class RecursiveFibonacciCalculator implements FibonacciCalculatorService {

    @Override
    public int[] getFibonacciNumber(int n) {
        return new int[]{fib(n)};
    }

    private static int fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

}
