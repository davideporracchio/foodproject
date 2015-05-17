package it.food.facade;

import it.food.model.CustomerBO;
import it.food.model.StatementBO;
import it.food.model.VisitBO;
import it.food.service.CustomerService;
import it.food.service.PointCalculator;
import it.food.service.StatementService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class StatementUpdaterImpl implements StatementUpdater {

	@Autowired
	private StatementService statementService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PointCalculator pointCalculator;

	
	@Override
	public StatementBO updatePoint(String fidelityCardId, double totalAmount) {
		StatementBO statementBO = findStatementByFidelityCardId(fidelityCardId);

		VisitBO visit = new VisitBO();
		visit.setDateVisit(LocalDateTime.now());
		visit.setMoneySpent(totalAmount);
		pointCalculator.calculatePointForVisit(statementBO, visit);
		visit.setStatementBO(statementBO);
		statementService.save(statementBO);
		return statementBO;
	}

	
	@Override
	public StatementBO getStatementByFidelityCardId(String fidelityCardId) {
		StatementBO statementBO = findStatementByFidelityCardId(fidelityCardId);

		return statementBO;
	}


	private StatementBO findStatementByFidelityCardId(String fidelityCardId) {
		CustomerBO customerBO = customerService.findCustomer(fidelityCardId);
		
		if (customerBO == null){
			customerBO  =new CustomerBO();
			customerBO.setFidelityCardID(fidelityCardId);
			customerService.save(customerBO);
		}
		
		StatementBO statementBO = statementService.findStatementByCustomer(customerBO);

		if (statementBO == null) {
			statementBO = statementService.createFromPreviousMonth(customerBO);
		}
		return statementBO;
	}
}
