package com.katovs.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PrimeNumberControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
                .andExpect(content().string("Welcome to the Prime Number Service!"));
    }

    @Test
    public void getPrimesJson() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
                .andExpect(content().json("{\"initial\":100,\"primes\":[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97]}"));
    }

    @Test
    public void getPrimesXml() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/10").accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/xml;charset=UTF-8"))
                .andExpect(content().xml("<PrimesResponse><initial>10</initial><primes><primes>2</primes><primes>3</primes><primes>5</primes><primes>7</primes></primes></PrimesResponse>"));
    }
    
    @Test
    public void getPrimesEdgeCasesJson() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"initial\":1,\"primes\":[]}")));

        mvc.perform(MockMvcRequestBuilders.get("/primes/0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"initial\":0,\"primes\":[]}")));

        mvc.perform(MockMvcRequestBuilders.get("/primes/-1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"initial\":-1,\"primes\":[]}")));

        mvc.perform(MockMvcRequestBuilders.get("/primes/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"initial\":2,\"primes\":[2]}")));

    }

    @Test
    public void getPrimesInvalidAlgo() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/1008?algorithm=invalid").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isIAmATeapot())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"));
    }

    @Test
    public void getPrimesInvalidArgumentType() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/primes/100b").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"));
    }
}