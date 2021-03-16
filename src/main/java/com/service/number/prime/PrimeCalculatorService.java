package com.service.number.prime;

import java.util.List;

public interface PrimeCalculatorService {

    List<Integer> getPrimes(int maxNumber);

    PrimeCalculatorStrategyName getStrategyName();

}
