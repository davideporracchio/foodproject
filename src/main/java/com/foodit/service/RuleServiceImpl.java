package com.foodit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodit.model.RuleBO;
import com.foodit.service.repository.RuleRepository;
@Service
public class RuleServiceImpl implements RuleService {

	@Autowired
	private RuleRepository ruleRepository;

	
	@Override
	public void save(RuleBO ruleBO) {
		ruleRepository.save(ruleBO);
	}

}
