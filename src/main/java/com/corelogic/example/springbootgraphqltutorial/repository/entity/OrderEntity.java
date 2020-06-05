package com.corelogic.example.springbootgraphqltutorial.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long customerId;

  private Long productId;

  private Integer quantity;

  private String status;

  private LocalDate created;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderEntity that = (OrderEntity) o;
    return Objects.equals(id, that.id)
        && Objects.equals(customerId, that.customerId)
        && Objects.equals(productId, that.productId)
        && Objects.equals(quantity, that.quantity)
        && Objects.equals(status, that.status)
        && Objects.equals(created, that.created);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customerId, productId, quantity, status, created);
  }

  @Override
  public String toString() {
    return "OrderEntity{"
        + "id="
        + id
        + ", customerId="
        + customerId
        + ", productId="
        + productId
        + ", quantity="
        + quantity
        + ", status='"
        + status
        + '\''
        + ", created="
        + created
        + '}';
  }
}
