package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;

@Repository
public class PendingOrdersRepository {

	private RedisTemplate<String, Object> redisTemplate;

	public PendingOrdersRepository(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	public void add(PizzaOrder order) {
		redisTemplate.opsForValue().set("name", order.getName());
		redisTemplate.opsForValue().set("email", order.getEmail());

	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	public boolean delete(String orderId) {
		return false;
	}

}
