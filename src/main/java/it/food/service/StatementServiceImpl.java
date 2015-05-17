package it.food.service;

import it.food.exception.ValidationException;
import it.food.model.CustomerBO;
import it.food.model.StatementBO;
import it.food.model.VisitBO;
import it.food.service.repository.StatementRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class StatementServiceImpl implements StatementService {

	@Autowired
	private StatementRepository statementRepository;

	private DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyyMM");

	@Override
	public StatementBO findStatementByCustomer(CustomerBO customer) {
		if(customer == null) throw new ValidationException("Customer can not be null");
		String yearMonthDate = yearMonthFormatter.format(LocalDateTime.now());
		return statementRepository.findByCustomerAndStringDate(customer, yearMonthDate);

	}

	@Override
	public void save(StatementBO statement) {
		statementRepository.save(statement);
	}

	@Override
	public StatementBO createFromPreviousMonth(CustomerBO customer) {

		LocalDateTime previousMonth = LocalDateTime.now().minusMonths(1);
		String yearMonthDatePrevious = yearMonthFormatter.format(previousMonth);
		StatementBO statementBO = statementRepository.findByCustomerAndStringDate(customer, yearMonthDatePrevious);
		StatementBO statementBONew = new StatementBO();
		if (statementBO != null) {
			statementBONew.setEndingPoints(statementBO.getEndingPoints());
			statementBONew.setStartingPoints(statementBO.getEndingPoints());
		}
		statementBONew.setCustomer(customer);
		statementBONew.setListVisits(new ArrayList<VisitBO>());
		statementBONew.setValuePoints(0);
		String yearMonthDate = yearMonthFormatter.format(LocalDateTime.now());
		statementBONew.setStringDate(yearMonthDate);
		statementRepository.save(statementBONew);
		return statementBONew;
	}
}
