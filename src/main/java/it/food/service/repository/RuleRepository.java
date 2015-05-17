package it.food.service.repository;

import it.food.model.RuleBO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends CrudRepository<RuleBO, Long> {

	List<RuleBO> findByActiveRuleTrue();
}