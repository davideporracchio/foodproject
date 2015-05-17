package com.foodit.service;

import java.time.LocalDateTime
import java.time.Month

import org.junit.Assert.*

import spock.lang.Specification

import com.foodit.exception.ValidationException
import com.foodit.model.RuleBO
import com.foodit.model.StatementBO
import com.foodit.model.VisitBO
import com.foodit.model.RuleBO.RuleType
import com.foodit.service.repository.RuleRepository


class PointCalculatorTest extends Specification{

	private PointCalculatorImpl underTest

	private RuleRepository ruleRepository;


	def setup() {
		ruleRepository =Mock(RuleRepository)

		underTest = new PointCalculatorImpl(ruleRepository:ruleRepository)
	}

	def "test calculatePointForVisit success when standard rule applied"() {
		given:
		double total = 10;
		StatementBO statementBO = new StatementBO()
		VisitBO visitBO = new VisitBO();
		visitBO.setDateVisit(LocalDateTime.of(2015, Month.MAY, 16,12,39));
		visitBO.setMoneySpent(total);
		List<RuleBO> listRules = new ArrayList<RuleBO>()
		loadRules(listRules)

		when:
		underTest.calculatePointForVisit(statementBO, visitBO)
		then:
		statementBO.getEndingPoints()==20
		statementBO.getListVisits().size()==1
		statementBO.getListVisits().get(0).getListRules().get(0).description=="Receive 1 point every pound spent"
		statementBO.getValuePoints()==0.4
	}

	def "test calculatePointForVisit success when amount more than 100"() {
		given:
		double total = 100;
		StatementBO statementBO = new StatementBO()
		VisitBO visitBO = new VisitBO();
		visitBO.setDateVisit(LocalDateTime.of(2015, Month.MAY, 16,12,39));
		visitBO.setMoneySpent(total);
		List<RuleBO> listRules = new ArrayList<RuleBO>()
		loadRules(listRules)

		when:
		underTest.calculatePointForVisit(statementBO, visitBO)
		then:
		statementBO.getEndingPoints()==205
		statementBO.getListVisits().size()==1
		statementBO.getListVisits().get(0).getListRules().size()==2
		statementBO.getListVisits().get(0).getListRules().get(0).description=="Receive 1 point every pound spent"
		statementBO.getListVisits().get(0).getListRules().get(1).description=="Receive 5 points if you spend more than 100 pound"
		statementBO.getValuePoints()==4.1
	}

	def "test calculatePointForVisit success when day is wednesday before 18"() {
		given:
		double total = 10;
		StatementBO statementBO = new StatementBO()
		VisitBO visitBO = new VisitBO();
		visitBO.setDateVisit(LocalDateTime.of(2015, Month.MAY, 13,12,39));
		visitBO.setMoneySpent(total);
		List<RuleBO> listRules = new ArrayList<RuleBO>()
		loadRules(listRules)

		when:
		underTest.calculatePointForVisit(statementBO, visitBO)
		then:
		statementBO.getEndingPoints()==40
		statementBO.getListVisits().size()==1
		statementBO.getListVisits().get(0).getListRules().size()==2
		statementBO.getListVisits().get(0).getListRules().get(0).description=="Receive 1 point every pound spent"
		statementBO.getListVisits().get(0).getListRules().get(1).description=="Double points if you buy before 6pm on Wednesday"
		statementBO.getValuePoints()==0.8
	}

	def "test calculatePointForVisit success when this is the third visit in the month"() {
		given:
		double total = 10;
		StatementBO statementBO = new StatementBO()
		loadVisit(statementBO)
		VisitBO visitBO = new VisitBO();
		visitBO.setDateVisit(LocalDateTime.of(2015, Month.MAY, 24,12,39));
		visitBO.setMoneySpent(total);
		List<RuleBO> listRules = new ArrayList<RuleBO>()
		loadRules(listRules)

		when:
		underTest.calculatePointForVisit(statementBO, visitBO)
		then:
		statementBO.getEndingPoints()==25
		statementBO.getListVisits().size()==3
		statementBO.getListVisits().get(2).getListRules().size()==2
		statementBO.getListVisits().get(2).getListRules().get(0).description=="Receive 1 point every pound spent"
		statementBO.getListVisits().get(2).getListRules().get(1).description=="Receive 5 points every 3rd visit in the month"
		statementBO.getValuePoints()==0.5
	}


	def "test validateStatement throws Exception when statement is null"() {
		given:

		when:
		underTest.validateStatement(null)
		then:
		final ValidationException exception = thrown()
		exception.message == 'Statement can not be null'
	}

	def "test validateVisit throws Exception when validation is null"() {
		given:

		when:
		underTest.validateVisit(null)
		then:
		final ValidationException exception = thrown()
		exception.message == 'Visit can not be null'
	}

	def "test validateVisit throws Exception when date in visit is null"() {
		given:
		VisitBO visit = new VisitBO()
		when:
		underTest.validateVisit(visit)
		then:
		final ValidationException exception = thrown()
		exception.message == 'Date of the visit be null'
	}

	def "test validateVisit throws Exception when Amount is negative"() {
		given:
		VisitBO visit = new VisitBO()
		visit.setDateVisit(LocalDateTime.now());
		visit.setMoneySpent(-9);
		when:
		underTest.validateVisit(visit)
		then:
		final ValidationException exception = thrown()
		exception.message == 'Amount spent needs to be a positive number'
	}


	private loadRules(List listRules) {
		RuleBO rule1 = new RuleBO(RuleType.ONTOTAL, 0, 2, null, 0, 1, "Receive 1 point every pound spent", true);
		RuleBO rule2 = new RuleBO(RuleType.ONTOTAL, 5, 0, null, 0, 100, "Receive 5 points if you spend more than 100 pound", true);
		RuleBO rule3 = new RuleBO(RuleType.ONDAYANDTIME, 0, 2, "WED 18", 0, 0, "Double points if you buy before 6pm on Wednesday", true);
		RuleBO rule4 = new RuleBO(RuleType.ONDAYANDTIME, 0, 2, "THU 18", 0, 0, "Double points if you buy before 6pm on Thursday", true);
		RuleBO rule5 = new RuleBO(RuleType.ONVISIT, 5, 0, null, 3, 0, "Receive 5 points every 3rd visit in the month", true);

		listRules.add(rule1)
		listRules.add(rule2)
		listRules.add(rule3)
		listRules.add(rule4)
		listRules.add(rule5)
		ruleRepository.findByActiveRuleTrue() >>  listRules
	}


	private void loadVisit(StatementBO statementBO ){

		VisitBO visitBO1 = new VisitBO();
		visitBO1.setDateVisit(LocalDateTime.of(2015, Month.MAY, 13,12,39));
		visitBO1.setMoneySpent(5);
		VisitBO visitBO2 = new VisitBO();
		visitBO2.setDateVisit(LocalDateTime.of(2015, Month.MAY, 10,10,30));
		visitBO2.setMoneySpent(5);
		statementBO.getListVisits().add(visitBO1);
		statementBO.getListVisits().add(visitBO2);
	}
}
