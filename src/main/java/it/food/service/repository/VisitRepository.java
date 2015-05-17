package it.food.service.repository;

import it.food.model.VisitBO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<VisitBO, Long> {

}