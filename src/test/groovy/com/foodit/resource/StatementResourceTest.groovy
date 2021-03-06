package com.foodit.resource;

import org.junit.Assert.*

import spock.lang.Specification

import com.foodit.facade.StatementUpdater
import com.foodit.model.StatementBO


class StatementResourceTest extends Specification{

	private StatementResource underTest

	private StatementUpdater statementUpdater;




	def setup() {
		statementUpdater =Mock(StatementUpdater)

		underTest = new StatementResource(statementUpdater:statementUpdater)
	}



	def "test getPoint success"() {
		given:
		String fidelityCardId = "ID"
		StatementBO statementBO= new StatementBO()

		when:
		String result = underTest.getPoint(fidelityCardId)
		then:
		1 * statementUpdater.getStatementByFidelityCardId(fidelityCardId) >> statementBO
		result !=null
	}

	def "test updateStatement success"() {
		given:
		String fidelityCardId = "ID"
		StatementBO statementBO= new StatementBO()
		String amount = "10"
		when:
		String result = underTest.updateStatement(fidelityCardId,amount)
		then:
		1 * statementUpdater.updatePoint(fidelityCardId,10) >> statementBO
		result !=null
	}


	def "test updateStatement fails when amount is not a number"() {
		given:
		String fidelityCardId = "ID"
		StatementBO statementBO= new StatementBO()
		String amount = "aa"
		when:
		String result = underTest.updateStatement(fidelityCardId,amount)
		then:
		0 * statementUpdater.updatePoint(fidelityCardId,10) >> statementBO
		result =="Amount needs to be a valid and positive number"
	}

	def "test updateStatement fails when amount is negative"() {
		given:
		String fidelityCardId = "ID"
		StatementBO statementBO= new StatementBO()
		String amount = "-10"
		when:
		String result = underTest.updateStatement(fidelityCardId,amount)
		then:
		0 * statementUpdater.updatePoint(fidelityCardId,10) >> statementBO
		result =="Amount needs to be a valid and positive number"
	}
}
