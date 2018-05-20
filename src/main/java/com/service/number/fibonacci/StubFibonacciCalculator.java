package com.service.number.fibonacci;

import org.springframework.stereotype.Component;

@Component
class StubFibonacciCalculator implements FibonacciCalculatorService {

    @Override
    public int[] getFibonacciNumber(int maxNumber) {
        //stub
        return new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};
    }

}
