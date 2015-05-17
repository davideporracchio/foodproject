package com.foodit.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodit.model.VisitBO;

@Repository
public interface VisitRepository extends CrudRepository<VisitBO, Long> {

}