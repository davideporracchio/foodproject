package it.food.resource;

import it.food.facade.StatementUpdater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatementResource {

	@Autowired
	private StatementUpdater statementUpdater;

	@RequestMapping(value = "/point/{fidelityCardId}", method = RequestMethod.PUT)
	public @ResponseBody String updateStatement(@PathVariable String fidelityCardId, @RequestBody String amount) {
		double total = 0;
		try{
			total=Double.parseDouble(amount);
			if (total<0) throw new Exception();
		}catch(Exception e){
			return "Amount needs to be a valid and positive number";
		}
		
		
		return statementUpdater.updatePoint(fidelityCardId, total).toString();
	}


	@RequestMapping("/point/{fidelityCardId}")
	public String getPoint(@PathVariable String fidelityCardId) {
		return statementUpdater.getStatementByFidelityCardId(fidelityCardId).toString();

	}
}
