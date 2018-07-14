package com.bootshop.service;

import java.util.List;

import com.bootshop.model.CartItem;

public interface CartItemService {
	void addCartItem(CartItem cartItem);
	void addOrUpdateCartItem(List<CartItem> cartItems);
	void addOrUpdateCartItems(List<CartItem> cartItems);
	void deleteByProductid(int productid);
	void deleteByCartid(String cartid);
	
}
