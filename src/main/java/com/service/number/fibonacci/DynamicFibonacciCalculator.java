package com.service.number.fibonacci;

import org.springframework.stereotype.Component;

@Component
class DynamicFibonacciCalculator implements FibonacciCalculatorService {

    @Override
    public int[] getFibonacciNumber(int n) {
        int[] f = new int[n];
        f[0] = f[1] = 1;

        for (int i = 2; i < n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;

    }


}
