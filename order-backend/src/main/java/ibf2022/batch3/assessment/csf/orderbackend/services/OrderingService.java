package ibf2022.batch3.assessment.csf.orderbackend.services;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;

@Service
public class OrderingService {

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private PendingOrdersRepository pendingOrdersRepo;

	public static final String PRICING_URL = "https://pizza-pricing-production.up.railway.app";

	// TODO: Task 5
	// WARNING: DO NOT CHANGE THE METHOD'S SIGNATURE
	public PizzaOrder placeOrder(PizzaOrder order) throws OrderException {

		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("name", order.getName());
		form.add("email", order.getEmail());
		form.add("sauce", order.getSauce());
		form.add("size", String.valueOf(order.getSize()));
		form.add("thickCrust", String.valueOf(order.isThickCrust()));
		form.addAll("toppings", order.getTopplings());
		form.add("comments", order.getComments());

		RequestEntity<MultiValueMap<String, String>> req = RequestEntity
				.post(PRICING_URL)
				.accept(MediaType.TEXT_PLAIN)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.body(form);

		ResponseEntity<String> resp;
		RestTemplate template = new RestTemplate();
		try {

			resp = template.exchange(req, String.class);
		} catch (Exception exception) {
			throw exception;
		}
		String payload = resp.getBody();

		PizzaOrder pizzaOrder = new PizzaOrder();
		// pizzaOrder.setOrderId(payload.getString("orderId"));
		return null;
	}

	// For Task 6
	// WARNING: Do not change the method's signature or its implemenation
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		return ordersRepo.getPendingOrdersByEmail(email);
	}

	// For Task 7
	// WARNING: Do not change the method's signature or its implemenation
	public boolean markOrderDelivered(String orderId) {
		return ordersRepo.markOrderDelivered(orderId) && pendingOrdersRepo.delete(orderId);
	}

}
