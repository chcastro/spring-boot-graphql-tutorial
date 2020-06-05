package com.corelogic.example.springbootgraphqltutorial.resolver;

import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.repository.entity.CustomerEntity;
import com.corelogic.example.springbootgraphqltutorial.repository.repository.CustomerRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {
  private final CustomerRepository customerRepository;

  public Customer customerById(Long id) {
    return customerRepository.findById(id).map(this::modelToGraphQL).orElse(null);
  }

  private Customer modelToGraphQL(CustomerEntity customerModel) {
    Customer customer = new Customer();
    customer.setId(customerModel.getId());
    customer.setName(customerModel.getName());
    customer.setEmail(customerModel.getEmail());

    return customer;
  }
}
