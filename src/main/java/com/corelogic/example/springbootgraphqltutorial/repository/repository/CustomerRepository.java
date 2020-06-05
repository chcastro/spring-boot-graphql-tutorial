package com.corelogic.example.springbootgraphqltutorial.repository.repository;

import com.corelogic.example.springbootgraphqltutorial.repository.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {}
