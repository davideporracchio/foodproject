package com.foodit.service;

import com.foodit.model.StatementBO;
import com.foodit.model.VisitBO;

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