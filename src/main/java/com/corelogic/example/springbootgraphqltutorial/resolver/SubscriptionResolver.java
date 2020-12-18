package com.corelogic.example.springbootgraphqltutorial.resolver;

import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.repository.entity.CustomerEntity;
import com.corelogic.example.springbootgraphqltutorial.repository.repository.CustomerRepository;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class SubscriptionResolver implements GraphQLSubscriptionResolver {

  private final CustomerRepository customerRepository;

  public Publisher<Customer> allCustomers() {
    log.trace("This is a TRACE level message");
    log.debug("This is a DEBUG level message");
    log.info("This is an INFO level message");
    log.warn("This is a WARN level message");
    log.error("This is an ERROR level message");
    List<CustomerEntity> customers = customerRepository.findAll();
    Flux<CustomerEntity> customerEntityFlux = Flux.fromStream(customers.stream());
    Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(5));
    return Flux.zip(customerEntityFlux, durationFlux)
        .map(objects -> QueryResolver.modelToGraphQL(objects.getT1()));
  }
}
