package com.prime.service

import com.prime.service.EratosthenesSievePrimeCalculator
import org.junit.Test

class EratosthenesSievePrimeCalculatorTest {

    @Test
    void 'test computeSieve'() {
        def expected = [true, true, true, true, false, true, false, true, false, false, false]
        assert expected == EratosthenesSievePrimeCalculator.computeSieve(10)
    }

    @Test
    void 'test getPrimes base case'() {
        def expectedHundred = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
        EratosthenesSievePrimeCalculator calculator = new EratosthenesSievePrimeCalculator()

        assert expectedHundred == calculator.getPrimes(100)
        assert [2] == calculator.getPrimes(2)
        assert [] == calculator.getPrimes(1)
        assert [] == calculator.getPrimes(0)
        assert [] == calculator.getPrimes(-1)


    }
}
