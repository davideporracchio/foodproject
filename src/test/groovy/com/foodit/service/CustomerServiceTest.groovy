package com.foodit.service;

import org.junit.Assert.*

import spock.lang.Specification

import com.foodit.model.CustomerBO
import com.foodit.service.repository.CustomerRepository


class CustomerServiceTest extends Specification{

	private CustomerServiceImpl underTest
	private CustomerRepository customerRepository;
	def setup() {
		customerRepository =Mock(CustomerRepository)
		underTest = new CustomerServiceImpl(customerRepository:customerRepository)
	}

	def "test save success"() {
		given:
		CustomerBO customer = new CustomerBO()

		when:
		underTest.save(customer)
		then:
		1 * customerRepository.save(customer)
	}

	def "test findCustomer success"() {
		given:
		CustomerBO customer = new CustomerBO()
		String fidelityCardId = "ID"
		customer.setFidelityCardID(fidelityCardId)
		1 * customerRepository.findByFidelityCardID(fidelityCardId) >> customer
		when:
		CustomerBO result = underTest.findCustomer(fidelityCardId)
		then:
		result.getFidelityCardID() == fidelityCardId
	}
}
