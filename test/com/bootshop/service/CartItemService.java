package com.bootshop.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootshop.model.Cart;
import com.bootshop.model.CartItem;

@Component
public class CartItemService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(CartItemService.class);

	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	ProductService productService;

	@PostConstruct
	public void dataInit() {
		CartItem cartItem=new CartItem();
		cartItem.setProduct(productService.getProductById(7));
		cartItem.setCart(new Cart());
		cartItem.setTotalPrice(productService.getProductById(7)
				.getPrice());
		cartItem.setQuantity(1);


		logger.info("id is {}" );

	}

}
