package com.foodit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodit.model.CustomerBO;
import com.foodit.service.repository.CustomerRepository;
@Service
class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	
	@Override
	public CustomerBO findCustomer(String fidelityCardId) {
		return customerRepository.findByFidelityCardID(fidelityCardId);

	}
	
	
	@Override
	public void save(CustomerBO customerBO) {
		customerRepository.save(customerBO);

	}

}
