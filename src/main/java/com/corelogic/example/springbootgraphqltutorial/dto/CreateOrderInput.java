package com.corelogic.example.springbootgraphqltutorial.dto;

import lombok.Data;

@Data
public class CreateOrderInput {
  private Long customerId;
  private Long productId;
  private Integer quantity;
}
