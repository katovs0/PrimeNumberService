package com.service.number.fibonacci;

import org.springframework.stereotype.Component;

@Component
class SpaceOptimizedFibonacciCalculator implements FibonacciCalculatorService {

    @Override
    public int[] getFibonacciNumber(int n) {
        return new int[]{fib(n)};
    }

    private static int fib(int n) {
        int a = 1, b = 1, c;

        if (n < 1) {
            return a;
        }
        for (int i = 2; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;

    }


}
