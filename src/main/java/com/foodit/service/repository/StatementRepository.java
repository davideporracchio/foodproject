package com.foodit.service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodit.model.CustomerBO;
import com.foodit.model.StatementBO;

@Repository
public interface StatementRepository extends CrudRepository<StatementBO, Long> {

	List<StatementBO> findByCustomer(CustomerBO customer);

	StatementBO findByCustomerAndStringDate(CustomerBO customer, String stringDate);
}