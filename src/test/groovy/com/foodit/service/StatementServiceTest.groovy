package com.foodit.service;

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import org.junit.Assert.*

import spock.lang.Specification

import com.foodit.exception.ValidationException
import com.foodit.model.CustomerBO
import com.foodit.model.StatementBO
import com.foodit.service.repository.StatementRepository


class StatementServiceTest extends Specification{

	private StatementServiceImpl underTest

	private StatementRepository statementRepository;

	private DateTimeFormatter yearMonthFormatter = DateTimeFormatter.ofPattern("yyyyMM");

	def setup() {
		statementRepository =Mock(StatementRepository)
		underTest = new StatementServiceImpl(statementRepository:statementRepository)
	}

	def "test save success"() {
		given:
		StatementBO statement = new StatementBO()
		when:
		underTest.save(statement)
		then:
		1 * statementRepository.save(statement)
	}



	def "test findStatementByCustomer success"() {
		given:
		String yearMonthDate = yearMonthFormatter.format(LocalDateTime.now());
		CustomerBO customer = new CustomerBO()
		String fidelityCardId = "ID"
		customer.setFidelityCardID(fidelityCardId)
		StatementBO statementBO = new StatementBO()
		statementBO.setId(1);
		1 * statementRepository.findByCustomerAndStringDate(customer,yearMonthDate) >> statementBO
		when:
		StatementBO result = underTest.findStatementByCustomer(customer)
		then:
		result.getId() == 1
	}

	def "test findStatementByCustomer thows validationException"() {
		given:
		String yearMonthDate = yearMonthFormatter.format(LocalDateTime.now());
		when:
		StatementBO result = underTest.findStatementByCustomer(null)
		then:
		result ==null
		0 * statementRepository.findByCustomerAndStringDate(null,yearMonthDate)
		final ValidationException exception = thrown()
		exception.message == 'Customer can not be null'
	}

	def "test createFromPreviousMonth success"() {
		given:
		CustomerBO customerBO = new CustomerBO();
		StatementBO statementBO = new StatementBO();
		statementBO.setEndingPoints(5);
		statementRepository.findByCustomerAndStringDate(customerBO, _) >> statementBO

		when:
		StatementBO result = underTest.createFromPreviousMonth(customerBO)
		then:
		result.getEndingPoints() == 5
		result.getStartingPoints() == 5
		1 * statementRepository.save(_)
	}

	def "test createFromPreviousMonth success when first time statement"() {
		given:
		CustomerBO customerBO = new CustomerBO();
		StatementBO statementBO = new StatementBO();
		statementBO.setEndingPoints(5);
		statementRepository.findByCustomerAndStringDate(customerBO, _) >> null

		when:
		StatementBO result = underTest.createFromPreviousMonth(customerBO)
		then:
		result.getEndingPoints() == 0
		result.getStartingPoints() == 0
		1 * statementRepository.save(_)
	}
}
