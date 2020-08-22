package com.corelogic.example.springbootgraphqltutorial.service;

import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.repository.entity.CustomerEntity;
import com.corelogic.example.springbootgraphqltutorial.repository.repository.CustomerRepository;
import com.corelogic.example.springbootgraphqltutorial.resolver.OrderResolver;
import com.corelogic.example.springbootgraphqltutorial.resolver.QueryResolver;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor
public class SpringReactiveService {
  private final CustomerRepository customerRepository;

  public Publisher<Customer> allCustomers() {
    List<CustomerEntity> customers = customerRepository.findAll();
    Flux<CustomerEntity> customerEntityFlux = Flux.fromStream(customers.stream());
    Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(5));
    return Flux.zip(customerEntityFlux, durationFlux)
        .map(objects -> QueryResolver.modelToGraphQL(objects.getT1()));
  }
}
