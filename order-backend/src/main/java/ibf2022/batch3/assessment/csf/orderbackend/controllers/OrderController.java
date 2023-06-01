package ibf2022.batch3.assessment.csf.orderbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.services.OrderingService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")

public class OrderController {

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private PendingOrdersRepository pendingOrderRepo;

	@Autowired
	private OrderingService orderSvc;

	// TODO: Task 3 - POST /api/order
	@PostMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> placeOrder(@RequestBody PizzaOrder order) {
		ordersRepo.add(order);

		return ResponseEntity.status(HttpStatus.OK).body(null);

	}

	// TODO: Task 6 - GET /api/orders/<email>
	@GetMapping(path = "/orders/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getOrdersByEmail(@PathVariable String email) {
		List<PizzaOrder> pizzaOrder = orderSvc.getPendingOrdersByEmail(email);
		if (pizzaOrder != null) {
			return ResponseEntity.ok().body(null);
		} else {
			return ResponseEntity.status(404).body(null);
		}

	}

	// TODO: Task 7 - DELETE /api/order/<orderId>

}
