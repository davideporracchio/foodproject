package it.food.service;

import it.food.model.CustomerBO;
import it.food.model.StatementBO;

public interface StatementService {

	/**
	 * Find Current Statement given a customer
	 * 
	 * @param customer
	 * @return StatementBO
	 */
	StatementBO findStatementByCustomer(CustomerBO customer);

	/**
	 * Save a statement
	 * 
	 * @param statement
	 */

	void save(StatementBO statement);

	/**
	 * Create a statement for the current month using information from the previous month
	 * 
	 * @param customer
	 * @return StatementBO
	 */
	StatementBO createFromPreviousMonth(CustomerBO customer);

}