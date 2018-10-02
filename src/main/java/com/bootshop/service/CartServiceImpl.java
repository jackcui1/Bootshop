package com.bootshop.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootshop.model.Cart;
import com.bootshop.repository.CartRepository;
import com.bootshop.service.CartService;


@Service
@Transactional
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public void addCart(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public void editCart(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public Cart getCartById(String cartid) {
		return cartRepository.findOne(cartid);
	}

	@Override
	public Cart validate(String cartid) throws IOException {
		Cart cart=getCartById(cartid);
		
		if(cart==null ||(cart.getCartItems().size()==0)){
			throw new IOException(cartid+"");
		}
		return cart;
	}
	
}
