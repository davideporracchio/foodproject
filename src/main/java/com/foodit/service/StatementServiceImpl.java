package com.foodit.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodit.exception.ValidationException;
import com.foodit.model.CustomerBO;
import com.foodit.model.StatementBO;
import com.foodit.model.VisitBO;
import com.foodit.service.repository.StatementRepository;

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
