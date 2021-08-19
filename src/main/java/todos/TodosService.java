package todos;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import errors.NotFoundException;

@Service
public class TodosService {

	@Autowired(required = true)
	private TodoRepository todorep;

	public List<Order> findAll() {
		
//        Query query = new Query();
//        query.addCriteria(Criteria.where("accpted").is(false));
//		
		List<Order> orderTobeAccepted = todorep.findAll();
		
		List<Order> matchingObject = orderTobeAccepted.stream().filter(p -> p.isAccpted()==false)
				.map(p -> p)
				.collect(Collectors.toList());
		
		return matchingObject;
	}

	public List<Order> findAllAccpted() {
			
		List<Order> orderTobeAccepted = todorep.findAll();
		
		List<Order> matchingObject = orderTobeAccepted.stream().filter(p -> p.isAccpted()==true)
				.map(p -> p)
				.collect(Collectors.toList());
		
		return matchingObject;
	}
	
//	public Order getById(String id) {
//		try {
//			return todorep.findById(id).get();
//		} catch (NoSuchElementException ex) {
//			throw new NotFoundException(String.format("No Record with the id [%s] was found in our database", id));
//		}
//	}

	public boolean checkIfExist(Order order) {
		try {
			List<Order> results = todorep.findAll();
			
			Optional<Order> matchingObject = results.stream().
				    filter(p -> p.getDealAmount() == order.getDealAmount()
				    && p.getOrderingCurrency().equals(order.getOrderingCurrency())
				    && p.getToISOCode().equals(order.getToISOCode())).
				    findFirst();
			
			if(matchingObject.isPresent()) {
				return true;
			}
			
			return false;
		} catch (NoSuchElementException ex) {
			throw new NotFoundException(String.format("No Record with the id [%s] was found in our database"));
		}
	}
	
	public Order save(Order order) {
		order.setTimestamp(new Date());
		return todorep.insert(order);
	}

	public void delete(String id) {
		todorep.deleteById(id);
	}
	
	public Order update(String id) {
		
		Order myDocumentToUpdate = todorep.findById(id).get(); // fetching a document that you want to update the field
		myDocumentToUpdate.setAccpted(true); // setting the new value to the field myField
		return todorep.save(myDocumentToUpdate);
		
       
	}

}
