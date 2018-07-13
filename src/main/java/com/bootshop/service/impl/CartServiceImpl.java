package com.bootshop.service.impl;

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
	public Cart getCartById(int id) {
		return cartRepository.getOne(id);
	}

	@Override
	public void editCart(Cart cart) {
		cartRepository.save(cart);
	}

}
