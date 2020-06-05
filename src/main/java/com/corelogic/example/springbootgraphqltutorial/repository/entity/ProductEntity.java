package com.corelogic.example.springbootgraphqltutorial.repository.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {

  @Id @GeneratedValue private Long id;
  private String name;
  private String description;
  private Double price;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductEntity productModel = (ProductEntity) o;
    return Objects.equals(id, productModel.id)
        && Objects.equals(name, productModel.name)
        && Objects.equals(description, productModel.description)
        && Objects.equals(price, productModel.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, price);
  }

  @Override
  public String toString() {
    return "ProductEntity{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", price="
        + price
        + '}';
  }
}
