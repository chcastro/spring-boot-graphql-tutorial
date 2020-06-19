package com.corelogic.example.springbootgraphqltutorial.resolver;

import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.dto.Order;
import com.corelogic.example.springbootgraphqltutorial.dto.PageInput;
import com.corelogic.example.springbootgraphqltutorial.repository.entity.OrderEntity;
import com.corelogic.example.springbootgraphqltutorial.repository.repository.OrderRepository;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.corelogic.example.springbootgraphqltutorial.resolver.OrderMutationResolver.getOrder;

@Component
@AllArgsConstructor
public class CustomerResolver implements GraphQLResolver<Customer> {

  private final OrderRepository orderRepository;

  public List<Order> orders(Customer customer, String status, PageInput page) {
    if(Objects.nonNull(page)) {
      Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize());
      return orderRepository.findByCustomerId(customer.getId(), pageable)
              .stream()
              .map(this::orderToGraphQL)
              .collect(Collectors.toList());
    }

    if (Objects.isNull(status)){
      return orders(customer);
    }

    return orderRepository.findByCustomerIdAndStatusEquals(customer.getId(), status)
            .stream()
            .map(this::orderToGraphQL)
            .collect(Collectors.toList());
  }

  private List<Order> orders(Customer customer) {
    return orderRepository.findByCustomerId(customer.getId()).stream()
            .map(this::orderToGraphQL)
            .collect(Collectors.toList());
  }

  private Order orderToGraphQL(OrderEntity orderEntity) {
    return getOrder(orderEntity);
  }
}
