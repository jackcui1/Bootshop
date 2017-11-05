package com.bootshop.service;


import java.io.IOException;

import com.bootshop.model.Cart;

public interface CartService {
	void addCart(Cart cart);
	Cart getCartById(String cartid);
	void editCart(Cart cart);
	Cart validate(String cartid) throws IOException;
}
