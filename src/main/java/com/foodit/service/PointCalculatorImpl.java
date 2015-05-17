package com.foodit.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodit.exception.ValidationException;
import com.foodit.model.RuleBO;
import com.foodit.model.StatementBO;
import com.foodit.model.VisitBO;
import com.foodit.service.repository.RuleRepository;

@Service
class PointCalculatorImpl implements PointCalculator {

	DateTimeFormatter dayOfTheWeek = DateTimeFormatter.ofPattern("E");

	@Autowired
	private RuleRepository ruleRepository;

	static double VALUEPOINT = 0.02;

	@Override
	public void calculatePointForVisit(StatementBO statementBO, VisitBO visitBO) {

		validateStatement(statementBO);
		validateVisit(visitBO);

		int numberVisitsMonth = statementBO.getListVisits().size();

		ruleRepository.findByActiveRuleTrue().forEach(rule -> process(rule, visitBO, numberVisitsMonth));

		statementBO.getListVisits().add(visitBO);

		statementBO.setEndingPoints(statementBO.getEndingPoints() + visitBO.getPoint());

		statementBO.setValuePoints(statementBO.getEndingPoints() * VALUEPOINT);

	}

	void validateStatement(StatementBO statementBO) {
		if (statementBO == null)
			throw new ValidationException("Statement can not be null");
	}

	void validateVisit(VisitBO visitBO) {
		if (visitBO == null)
			throw new ValidationException("Visit can not be null");
		if (visitBO.getDateVisit() == null)
			throw new ValidationException("Date of the visit be null");
		if (visitBO.getMoneySpent() <= 0)
			throw new ValidationException("Amount spent needs to be a positive number");

	}

	private void process(RuleBO rule, VisitBO visit, int numberVisitsMonth) {

		List<RuleBO> listRules = visit.getListRules();
		Double money = visit.getMoneySpent();
		LocalDateTime time = visit.getDateVisit();
		int points = visit.getPoint();
		switch (rule.getRuleType()) {
		case ONDAYANDTIME:
			if (dayOfTheWeek.format(time).equalsIgnoreCase(rule.getDayAndTime().split(" ")[0])) {
				int hours = Integer.parseInt(rule.getDayAndTime().split(" ")[1]);
				if (time.getHour() < hours) {
					// apply the rule
					points = points + rule.getSumPoints();
					if (rule.getMoltiplyPoints() > 1) {
						points = points + (money.intValue() * rule.getMoltiplyPoints());
					}
					listRules.add(rule);
				}
			}
			break;
		case ONTOTAL:

			if (money >= rule.getAmountSpent()) {
				// apply the rule
				points = points + rule.getSumPoints();
				if (rule.getMoltiplyPoints() > 1) {
					points = points + (money.intValue() * rule.getMoltiplyPoints());
				}
				listRules.add(rule);
			}
			break;

		case ONVISIT:

			if (numberVisitsMonth != 0 && ((numberVisitsMonth + 1) % rule.getNumberVisit() == 0)) {
				// apply the rule
				points = points + rule.getSumPoints();
				if (rule.getMoltiplyPoints() > 1) {
					points = points + (money.intValue() * rule.getMoltiplyPoints());
				}
				listRules.add(rule);
			}
			break;
		default:
			break;
		}
		visit.setPoint(points);

	}
}
