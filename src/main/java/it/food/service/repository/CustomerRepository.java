package it.food.service.repository;

import it.food.model.CustomerBO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends CrudRepository<CustomerBO, Long> {

	CustomerBO findByFidelityCardID(String fidelityCardId);
}