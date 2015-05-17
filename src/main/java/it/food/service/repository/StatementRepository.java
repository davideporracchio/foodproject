package it.food.service.repository;

import it.food.model.CustomerBO;
import it.food.model.StatementBO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementRepository extends CrudRepository<StatementBO, Long> {

	List<StatementBO> findByCustomer(CustomerBO customer);

	StatementBO findByCustomerAndStringDate(CustomerBO customer, String stringDate);
}