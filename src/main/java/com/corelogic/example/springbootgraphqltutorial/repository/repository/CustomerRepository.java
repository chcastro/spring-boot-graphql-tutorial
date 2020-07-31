package com.corelogic.example.springbootgraphqltutorial.repository.repository;

import com.corelogic.example.springbootgraphqltutorial.repository.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {}
