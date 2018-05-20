package com.katovs.service.domain;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

@XmlRootElement
public class PrimesResponse {
    private int initial;
    private List<Integer> primes;

    public PrimesResponse(){}

    public PrimesResponse(int initial, List<Integer> primes) {
        this.initial = initial;
        this.primes = primes;
    }

    public int getInitial() {
        return initial;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public List<Integer> getPrimes() {
        return Collections.unmodifiableList(primes);
    }

    public void setPrimes(List<Integer> primes) {
        this.primes = primes;
    }

    @Override
    public String toString() {
        return "PrimesResponse{" +
                "initial=" + initial +
                ", primes=" + primes +
                '}';
    }
}
