package com.service.number.domain;

public class FibonacciResponse {
    private int initial;
    private int[] numbers;

    public FibonacciResponse() {
    }

    public FibonacciResponse(int initial, int[] numbers) {
        this.initial = initial;
        this.numbers = numbers;
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
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
