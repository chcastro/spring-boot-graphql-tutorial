package com.corelogic.example.springbootgraphqltutorial.resolver;

import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.repository.entity.CustomerEntity;
import com.corelogic.example.springbootgraphqltutorial.repository.repository.CustomerRepository;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Component
@AllArgsConstructor
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

  private final CustomerRepository customerRepository;

  public Publisher<Customer> allCustomers() {
    List<CustomerEntity> customers = customerRepository.findAll();
    Flux<CustomerEntity> customerEntityFlux = Flux.fromStream(customers.stream());
    Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(5));
    return Flux.zip(customerEntityFlux, durationFlux)
        .map(objects -> OrderResolver.modelToGraphQL(objects.getT1()));
  }
}
