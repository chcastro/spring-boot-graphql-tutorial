package com.corelogic.example.springbootgraphqltutorial.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Order {
  private Long id;
  private Customer customer;
  private Product product;
  private Integer quantity;
  private String status;
  private LocalDate created;
}
