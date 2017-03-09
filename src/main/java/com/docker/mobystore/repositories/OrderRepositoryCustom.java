package com.docker.mobystore.repositories;

import com.docker.mobystore.model.Order;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepositoryCustom {
	public List<Order> findByOrderNum(int orderNum);
}
