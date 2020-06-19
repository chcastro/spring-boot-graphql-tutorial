package com.corelogic.example.springbootgraphqltutorial.repository.repository;

import com.corelogic.example.springbootgraphqltutorial.repository.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {
  List<OrderEntity> findByCustomerId(Long customerId);

  List<OrderEntity> findByCustomerIdAndStatusEquals(Long customerId, String status);

  List<OrderEntity> findByCustomerId(Long customerId, Pageable page);
}
