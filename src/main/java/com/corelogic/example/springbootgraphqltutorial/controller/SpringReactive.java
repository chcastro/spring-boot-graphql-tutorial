package com.corelogic.example.springbootgraphqltutorial.controller;

import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.service.SpringReactiveService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/example")
@AllArgsConstructor
public class SpringReactive {

    private final SpringReactiveService springReactiveService;

    @GetMapping
    public Flux<Customer> getAllUsers(){
        return springReactiveService.allCustomers();
    }

    @GetMapping(value = "/client", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllUsersClient(){
        WebClient client = WebClient.create("http://localhost:8080");

        Flux<Customer> customerFlux = client.get()
                .uri("/example")
                .retrieve()
                .bodyToFlux(Customer.class);

        customerFlux.subscribe(System.out::println);

        return customerFlux;
    }
}
