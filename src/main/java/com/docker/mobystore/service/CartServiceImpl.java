package com.docker.mobystore.service;

import java.util.List;

import com.docker.mobystore.model.Cart;
import com.docker.mobystore.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("cartService")
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	
	public Cart findById(Long cartId) {
		return cartRepository.findOne(cartId);
	}

	public void saveCart(Cart cart) {
		cartRepository.save(cart);
	}

	public void updateCart(Cart cart) {
		updateCart(cart);
	}

	public void deleteItemById(Long productId) {
		cartRepository.delete(productId);
	}

	public void deleteAllItems() {
		cartRepository.deleteAll();
	}

	public boolean cartExists(Cart cart) {
		return findById(cart.getCartId()) != null;
	}


}
