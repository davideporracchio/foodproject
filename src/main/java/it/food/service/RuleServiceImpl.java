package it.food.service;

import it.food.model.RuleBO;
import it.food.service.repository.RuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RuleServiceImpl implements RuleService {

	@Autowired
	private RuleRepository ruleRepository;

	
	@Override
	public void save(RuleBO ruleBO) {
		ruleRepository.save(ruleBO);
	}

}
