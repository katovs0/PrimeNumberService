package com.katovs.service.domain;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement
public class FibonacciResponse {
    private int initial;
    private List<Integer> numbers;

    public FibonacciResponse(){}

    public FibonacciResponse(int initial, List<Integer> numbers) {
        this.initial = initial;
        this.numbers = numbers;
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "FibonacciResponse{" +
                "initial=" + initial +
                ", primes=" + numbers +
                '}';
    }
}
