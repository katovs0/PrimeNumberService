package com.service.number.fibonacci

import com.service.number.prime.BruteForcePrimeCalculator
import org.junit.Test

class RecursiveFibonacciCalculatorTest {


    @Test
    void 'test isPrimeBruteForce with primes'() {
        def fib = new RecursiveFibonacciCalculator()

        assert [1] == fib.getFibonacciNumber(1)
        assert [1] == fib.getFibonacciNumber(2)
        assert [2] == fib.getFibonacciNumber(3)
        assert [3] == fib.getFibonacciNumber(4)
        assert [5] == fib.getFibonacciNumber(5)
        assert [8] == fib.getFibonacciNumber(6)
        assert [13] == fib.getFibonacciNumber(7)
        assert [21] == fib.getFibonacciNumber(8)
        assert [34] == fib.getFibonacciNumber(9)
    }

}
