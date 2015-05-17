package it.food.service;

import it.food.model.CustomerBO;
import it.food.service.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
