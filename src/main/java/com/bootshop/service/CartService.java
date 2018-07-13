package com.bootshop.service;


import com.bootshop.model.Cart;

public interface CartService {
	void addCart(Cart cart);
	Cart getCartById(int id);
	void editCart(Cart cart);
	
}
