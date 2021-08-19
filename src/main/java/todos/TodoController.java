package todos;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.juli.logging.LogFactory;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping(value="/api/controllers/todos" ,headers = "Accept=application/json")
public class TodoController {
	final org.apache.juli.logging.Log logger = LogFactory.getLog(TodoController.class);
	
	private setHeaders header;
	public TodoController() {
		header = new setHeaders();
	}

	@Autowired
	private TodosService todosServices;

	@GetMapping(value = { "/", "" })
	public ResponseEntity<List<Order>> getAll() {
		logger.info("getAll working !");
		List<Order> result = todosServices.findAll();
		return new ResponseEntity<>(result, header.Headers(), HttpStatus.OK);
	}

	@GetMapping(value = { "accepted/", "" })
	public ResponseEntity<List<Order>> getAllAccepted() {
		logger.info("getAllAccepted working !");
		List<Order> result = todosServices.findAllAccpted();
		return new ResponseEntity<>(result, header.Headers(), HttpStatus.OK);
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Order> getOrderById(@PathVariable String id) {
//		Order result = todosServices.getById(id);
//		return new ResponseEntity<Order>(result, header.Headers(), HttpStatus.OK);
//	}

	@PostMapping(value = { "/", "" })
	public ResponseEntity<Object> createOrder(@Valid @RequestBody Order order) {
		logger.info("created working !" + order);
		HashMap<String, String> response = new HashMap<>();
		order.setAccpted(false);
		
		boolean isFound = todosServices.checkIfExist(order);
		
		
		if(isFound) {
			response.put("message", "already found");
			return new ResponseEntity<Object>(response, header.Headers(), HttpStatus.FOUND);
		} else {
			
			Order result = todosServices.save(order);
			response.put("message", "Saved successfully! " + result.getId());
			return new ResponseEntity<Object>(response, header.Headers(), HttpStatus.CREATED);
		}
		
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable String id) {
		todosServices.delete(id);
		return new ResponseEntity<>("deleted", header.Headers(), HttpStatus.OK);

	}
	
	@GetMapping("update/{id}")
	public ResponseEntity<Object> update(@PathVariable String id) {
		Order result = todosServices.update(id);
		HashMap<String, String> response = new HashMap<>();
		logger.info("accepted working !");

		if(result!=null) {
			response.put("message", "accepted");
			return new ResponseEntity<Object>(response, header.Headers(), HttpStatus.OK);
		} else {
			response.put("message", "not found");
			return new ResponseEntity<Object>(response, header.Headers(), HttpStatus.NOT_FOUND);
		}
	}
}
