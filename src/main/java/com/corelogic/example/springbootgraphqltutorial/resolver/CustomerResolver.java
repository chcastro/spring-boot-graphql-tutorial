package com.corelogic.example.springbootgraphqltutorial.resolver;

import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.dto.Order;
import com.corelogic.example.springbootgraphqltutorial.repository.entity.OrderEntity;
import com.corelogic.example.springbootgraphqltutorial.repository.repository.OrderRepository;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.corelogic.example.springbootgraphqltutorial.resolver.OrderMutationResolver.getOrder;

@Component
@AllArgsConstructor
public class CustomerResolver implements GraphQLResolver<Customer> {

  private final OrderRepository orderRepository;

  public List<Order> orders(Customer customer) {
    return orderRepository.findByCustomerId(customer.getId()).stream()
        .map(this::orderToGraphQL)
        .collect(Collectors.toList());
  }

  private Order orderToGraphQL(OrderEntity orderEntity) {
    return getOrder(orderEntity);
  }
}
