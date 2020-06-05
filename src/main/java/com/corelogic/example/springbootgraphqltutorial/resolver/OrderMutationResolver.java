package com.corelogic.example.springbootgraphqltutorial.resolver;

import com.corelogic.example.springbootgraphqltutorial.dto.CreateOrderInput;
import com.corelogic.example.springbootgraphqltutorial.dto.Customer;
import com.corelogic.example.springbootgraphqltutorial.dto.Order;
import com.corelogic.example.springbootgraphqltutorial.dto.Product;
import com.corelogic.example.springbootgraphqltutorial.repository.entity.OrderEntity;
import com.corelogic.example.springbootgraphqltutorial.repository.repository.OrderRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderMutationResolver implements GraphQLMutationResolver {

  private final OrderRepository orderRepository;

  public OrderMutationResolver(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @NotNull
  static Order getOrder(OrderEntity orderEntity) {
    Order order = new Order();
    order.setId(orderEntity.getId());
    order.setStatus(orderEntity.getStatus());
    order.setQuantity(orderEntity.getQuantity());
    order.setCreated(orderEntity.getCreated());

    Product product = new Product();
    product.setId(orderEntity.getProductId());
    order.setProduct(product);

    Customer customer = new Customer();
    customer.setId(orderEntity.getCustomerId());
    order.setCustomer(customer);

    return order;
  }

  public Order createOrder(CreateOrderInput createOrderInput) {
    OrderEntity order = new OrderEntity();

    order.setCustomerId(createOrderInput.getCustomerId());
    order.setProductId(createOrderInput.getProductId());
    order.setQuantity(createOrderInput.getQuantity());
    order.setStatus("PENDING");
    orderRepository.save(order);

    return orderToGraphQL(order);
  }

  private Order orderToGraphQL(OrderEntity orderEntity) {
    return getOrder(orderEntity);
  }
}
