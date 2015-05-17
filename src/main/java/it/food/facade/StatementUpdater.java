package it.food.facade;

import it.food.model.StatementBO;

public interface StatementUpdater {
	/**
	 * Update points for user using the amount spent and return the current statement updated
	 * @param fidelityCardId
	 * @param totalAmount
	 * @return StatementBO
	 */
	StatementBO updatePoint(String fidelityCardId, double totalAmount);
	/**
	 * Return the statement of the current month
	 * @param fidelityCardId
	 * @param totalAmount
	 * @return StatementBO
	 */
	StatementBO getStatementByFidelityCardId(String fidelityCardId);

}