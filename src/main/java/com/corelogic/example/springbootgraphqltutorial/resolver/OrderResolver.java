package com.corelogic.example.springbootgraphqltutorial.resolver;

import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.dto.Order;
import com.corelogic.example.springbootgraphqltutorial.dto.Product;
import com.corelogic.example.springbootgraphqltutorial.repository.entity.CustomerEntity;
import com.corelogic.example.springbootgraphqltutorial.repository.entity.ProductEntity;
import com.corelogic.example.springbootgraphqltutorial.repository.repository.CustomerRepository;
import com.corelogic.example.springbootgraphqltutorial.repository.repository.ProductRepository;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderResolver implements GraphQLResolver<Order> {

  private final ProductRepository productRepository;
  private final CustomerRepository customerRepository;

  public Customer customer(Order order) {
    return customerRepository
        .findById(order.getCustomer().getId())
        .map(OrderResolver::modelToGraphQL)
        .orElse(null);
  }

  public Product product(Order order) {
    return productRepository
        .findById(order.getProduct().getId())
        .map(this::modelToGraphQL)
        .orElse(null);
  }

  public static Customer modelToGraphQL(CustomerEntity customerEntity) {
    Customer customer = new Customer();
    customer.setEmail(customerEntity.getEmail());
    customer.setId(customerEntity.getId());
    customer.setName(customerEntity.getName());
    return customer;
  }

  private Product modelToGraphQL(ProductEntity productEntity) {
    Product product = new Product();
    product.setDescription(productEntity.getDescription());
    product.setName(productEntity.getName());
    product.setId(productEntity.getId());
    product.setPrice(productEntity.getPrice());
    return product;
  }
}
