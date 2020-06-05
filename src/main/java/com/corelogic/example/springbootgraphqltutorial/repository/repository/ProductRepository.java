package com.corelogic.example.springbootgraphqltutorial.repository.repository;

import com.corelogic.example.springbootgraphqltutorial.repository.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {}
