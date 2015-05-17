package com.foodit.service;

import org.junit.Assert.*

import spock.lang.Specification

import com.foodit.model.RuleBO
import com.foodit.service.repository.RuleRepository


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
