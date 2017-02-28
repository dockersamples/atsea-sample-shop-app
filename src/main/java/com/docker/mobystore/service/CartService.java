package com.docker.mobystore.service;

import com.docker.mobystore.model.Cart;
import com.docker.mobystore.model.Product;

public interface CartService {

	Cart findOne(Long cartId);
	
	void saveCart(Cart cart);
	
	void updateCart(Cart cart);
	
	void deleteItemById(Long productId);
	
	void deleteAllItems();
	
}
