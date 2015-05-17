package it.food.service;

import it.food.model.StatementBO;
import it.food.model.VisitBO;

public interface PointCalculator {
	/**
	 * This is the logic to calculate the point after the visit base on the rule
	 * we can apply
	 * 
	 * @param statementBO
	 * @param visit visit contains the amount spent and the date
	 */
	void calculatePointForVisit(StatementBO statementBO, VisitBO visit);

}