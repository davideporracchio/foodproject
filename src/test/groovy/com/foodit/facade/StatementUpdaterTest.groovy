package com.foodit.facade;

import org.junit.Assert.*

import spock.lang.Specification

import com.foodit.model.CustomerBO
import com.foodit.model.StatementBO
import com.foodit.service.CustomerService
import com.foodit.service.PointCalculator
import com.foodit.service.StatementService


class StatementUpdaterTest extends Specification{

	private StatementUpdaterImpl underTest

	private StatementService statementService;

	private CustomerService customerService;

	private PointCalculator pointCalculator;

	def setup() {
		pointCalculator =Mock(PointCalculator)
		statementService =Mock(StatementService)
		customerService =Mock(CustomerService)
		underTest = new StatementUpdaterImpl(pointCalculator:pointCalculator,customerService:customerService,statementService:statementService)
	}



	def "test getStatementByFidelityCardId success when statement exist in the current month"() {
		given:
		CustomerBO customerBO = new CustomerBO()
		String fidelityCardId = "ID"
		customerBO.setFidelityCardID(fidelityCardId)
		customerService.findCustomer(fidelityCardId) >> customerBO
		StatementBO statementBO = new StatementBO()
		statementBO.setEndingPoints(5)
		statementBO.setCustomer(customerBO)
		statementService.findStatementByCustomer(customerBO) >> statementBO

		when:
		StatementBO result = underTest.getStatementByFidelityCardId(fidelityCardId)
		then:
		result.getCustomer().getFidelityCardID() == fidelityCardId
		result.getEndingPoints() == 5
	}


	def "test getStatementByFidelityCardId success when statement does not exist and needs to be created from the privious month"() {
		given:
		CustomerBO customerBO = new CustomerBO()
		String fidelityCardId = "ID"
		customerBO.setFidelityCardID(fidelityCardId)
		customerService.findCustomer(fidelityCardId) >> customerBO
		StatementBO statementBO = new StatementBO()
		statementBO.setEndingPoints(50)
		statementBO.setCustomer(customerBO)
		statementService.findStatementByCustomer(customerBO) >> null

		when:
		StatementBO result = underTest.getStatementByFidelityCardId(fidelityCardId)
		then:
		1* statementService.createFromPreviousMonth(customerBO) >>statementBO
		result.getCustomer().getFidelityCardID() == fidelityCardId
		result.getEndingPoints() == 50
	}

	def "test updatePoint success when customer exists"() {
		given:
		CustomerBO customerBO = new CustomerBO()
		String fidelityCardId = "ID"
		customerBO.setFidelityCardID(fidelityCardId)
		customerService.findCustomer(fidelityCardId) >> customerBO
		StatementBO statementBO = new StatementBO()
		statementBO.setEndingPoints(5)
		statementBO.setCustomer(customerBO)
		statementService.findStatementByCustomer(customerBO) >> statementBO


		when:
		StatementBO result = underTest.updatePoint(fidelityCardId,10)
		then:
		1 * pointCalculator.calculatePointForVisit(statementBO, _);
		1 * statementService.save(statementBO);
	}

	def "test updatePoint success when customer does not exists"() {
		given:

		String fidelityCardId = "ID"

		customerService.findCustomer(fidelityCardId) >> null
		StatementBO statementBO = new StatementBO()
		statementBO.setEndingPoints(5)
		statementService.findStatementByCustomer(_) >> statementBO

		when:
		StatementBO result = underTest.updatePoint(fidelityCardId,10)
		then:
		1 * pointCalculator.calculatePointForVisit(statementBO, _);
		1 * statementService.save(statementBO);
		1 * customerService.save(_);
	}
}
