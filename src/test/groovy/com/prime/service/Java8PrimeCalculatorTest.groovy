package com.prime.service

import com.prime.service.Java8PrimeCalculator
import org.junit.Test

class Java8PrimeCalculatorTest {


    @Test
    void 'test isPrimeBruteForce with primes'() {
        for (int n : [2, 5, 7, 13, 97]) {
            assert Java8PrimeCalculator.isPrimeJava8(n)
        }
    }

    @Test
    void 'test isPrimeBruteForce with non-primes'() {
        for (int n : [-1, 0, 1, 4, 20, 33, 68]) {
            assert !Java8PrimeCalculator.isPrimeJava8(n)
        }
    }

    @Test
    void 'test getPrimes base case'() {
        def expectedHundred = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
        Java8PrimeCalculator calculator = new Java8PrimeCalculator()

        assert expectedHundred == calculator.getPrimes(100)
        assert [2] == calculator.getPrimes(2)
        assert [] == calculator.getPrimes(1)
        assert [] == calculator.getPrimes(0)
        assert [] == calculator.getPrimes(-1)


    }
}
