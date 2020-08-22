package com.corelogic.example.springbootgraphqltutorial.controller;

import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.service.SpringReactiveService;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/example")
@AllArgsConstructor
public class SpringReactive {

  private final SpringReactiveService springReactiveService;

  @GetMapping
  public Publisher<Customer> getAllUsers() {
    return springReactiveService.allCustomers();
  }

  @GetMapping(value = "/client", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Customer> getAllUsersClient() {
    WebClient client = WebClient.create("http://localhost:8080");

    Flux<Customer> customerFlux =
        client.get().uri("/example").retrieve().bodyToFlux(Customer.class);

    customerFlux.subscribe(System.out::println);

    return customerFlux;
  }

  @GetMapping(value = "/client/graphql", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Customer> getAllUsersClientGraphql() {
    WebClient client = WebClient.create("http://localhost:8080");

    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("query", "subscription { allCustomers { id name email } }");

    Flux<Customer> customerFlux =
        client
            .post()
            .uri("/graphql")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromMultipartData(body))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(Customer.class);

    customerFlux.subscribe(System.out::println);

    return customerFlux;
  }
}
