package com.katovs.service.fibonacci;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
class StubFibonacciCalculator implements FibonacciCalculatorService {

    @Override
    public List<Integer> getFibonacciNumber(int maxNumber) {
        //stub
        return Arrays.asList(1,1,2,3,5,8,13,21,34,55,89,144);
    }

}
