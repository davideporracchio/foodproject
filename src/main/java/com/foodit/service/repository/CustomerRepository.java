package com.foodit.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodit.model.CustomerBO;
@Repository
public interface CustomerRepository extends CrudRepository<CustomerBO, Long> {

	CustomerBO findByFidelityCardID(String fidelityCardId);
}