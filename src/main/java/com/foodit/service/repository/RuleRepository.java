package com.foodit.service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodit.model.RuleBO;

@Repository
public interface RuleRepository extends CrudRepository<RuleBO, Long> {

	List<RuleBO> findByActiveRuleTrue();
}