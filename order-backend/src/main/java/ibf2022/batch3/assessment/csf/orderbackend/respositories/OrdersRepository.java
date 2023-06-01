package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;

@Repository
public class OrdersRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for add()
	/*
	 * db.orders.insertOne({
	 * name: 'john',
	 * email: "john@gmail.com",
	 * sauce: "bbq",
	 * size: 1,
	 * crust: "thick",
	 * toppings: ["1", "2", "3"],
	 * comments: ""
	 * });
	 */
	public void add(PizzaOrder order) {

		Document document = new Document()
				.append("name", order.getName())
				.append("email", order.getEmail())
				.append("sauce", order.getSauce())
				.append("size", order.getSize())
				.append("crust", order.isThickCrust())
				.append("toppings", order.getTopplings())
				.append("comments", order.getComments());

		mongoTemplate.insert(document, "orders");

	}

	// TODO: Task 6
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for getPendingOrdersByEmail()
	/*
	 * db.orders.find({
	 * email:
	 * })
	 * .projection({ orderId: 1, total: 1, date:1, _id: 0 })
	 * .sort({date: -1 })
	 */
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		Criteria criteria = Criteria.where("email");
		Query query = Query.query(criteria)
				.with(Sort.by(Direction.DESC, "date"));
		query.fields()
				.include("orderId", "total", "date")
				.exclude("_id");

		List<PizzaOrder> orderList = mongoTemplate.find(query, PizzaOrder.class, "orders");

		return orderList;

	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for markOrderDelivered()
	public boolean markOrderDelivered(String orderId) {

		return false;
	}

}
