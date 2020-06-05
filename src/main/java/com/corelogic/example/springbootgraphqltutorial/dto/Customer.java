package com.corelogic.example.springbootgraphqltutorial.dto;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
  private Long id;
  private String name;
  private String email;
  private List<Order> orders;
}
