package it.food.service;

import it.food.model.RuleBO
import it.food.service.repository.RuleRepository

import org.junit.Assert.*

import spock.lang.Specification


class RuleServiceTest extends Specification{

	private RuleService underTest
	private RuleRepository ruleRepository;

	def setup() {
		ruleRepository =Mock(RuleRepository)
		underTest = new RuleServiceImpl(ruleRepository:ruleRepository)
	}

	def "test save success"() {
		given:
		RuleBO rule = new RuleBO()

		when:
		underTest.save(rule)
		then:
		1 * ruleRepository.save(rule)
	}
}
