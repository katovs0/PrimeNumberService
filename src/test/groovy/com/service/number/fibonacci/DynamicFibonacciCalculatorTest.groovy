package com.service.number.fibonacci

import org.junit.Test

class DynamicFibonacciCalculatorTest {


    @Test
    void 'test isPrimeBruteForce with primes'() {
        def fib = new DynamicFibonacciCalculator()

        assert [1,1,2,3,5,8,13,21,34,55] == fib.getFibonacciNumber(10)
    }

}
