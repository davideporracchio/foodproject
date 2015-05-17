package it.food.service;

import it.food.model.CustomerBO;

public interface CustomerService {

	/**
	 * Find customer by fidelity card id
	 * @param fidelityCardId
	 * @return CustomerBO
	 */
	CustomerBO findCustomer(String fidelityCardId);
	/**
	 * Save Customer
	 * @param customerBO
	 */
	void save(CustomerBO customerBO);

}