package com.katovs.service;

import com.katovs.service.domain.FibonacciResponse;
import com.katovs.service.domain.PrimesResponse;
import com.katovs.service.exception.IllegalAlgorithmException;
import com.katovs.service.fibonacci.FibonacciCalculatorService;
import com.katovs.service.prime.PrimeCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FibonacciNumbersController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext context;

    @GetMapping(value = "/fibonacci/{maxNumber}",
            produces = {"application/json", "application/xml"})
    public FibonacciResponse getFibonacciResponse(@PathVariable int maxNumber,
                                    @RequestParam(name = "algorithm",
                                            required = false,
                                            defaultValue = "stub") String algorithm) {
        logger.info("Start retrieving list of fibonacci numbers until {} [algorithm={}]", maxNumber, algorithm);
        List result;
        long start = System.currentTimeMillis();

        try {
            result = context.getBean(algorithm + "FibonacciCalculator", FibonacciCalculatorService.class).getFibonacciNumber(maxNumber);
        } catch (NoSuchBeanDefinitionException e) {
            logger.warn("Invalid algorithm requested [{}] ", algorithm);
            throw new IllegalAlgorithmException(algorithm);
        }

        logger.info("Finished retrieving list of fibonacci numbers until {} [algorithm={}] took {} ms.",
                maxNumber,
                algorithm,
                System.currentTimeMillis() - start);

        return new FibonacciResponse(maxNumber, result);
    }

    @GetMapping(value = "/fibonacci/",
            produces = {"application/json", "application/xml"})
    public FibonacciResponse getFibonacciResponse() {
        return getFibonacciResponse(10, "stub");
    }

}