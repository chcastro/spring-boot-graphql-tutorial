package com.corelogic.example.springbootgraphqltutorial.repository.repository;

import com.corelogic.example.springbootgraphqltutorial.repository.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
  List<OrderEntity> findByCustomerId(Long customerId);
}
