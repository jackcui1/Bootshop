package com.bootshop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootshop.model.CartItem;
import com.bootshop.repository.CartItemRepository;
import com.bootshop.service.CartItemService;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	CartItemRepository cartItemRepository;

	@Override
	public void addCartItem(CartItem cartItem) {
		cartItemRepository.save(cartItem);
	}

	@Override
	public void deleteByProductid(int productid) {
		cartItemRepository.deleteByProductid(productid);
		
	}

	@Override
	public void addOrUpdateCartItem(List<CartItem> cartItems) {
		cartItemRepository.save(cartItems);
	}

	@Override
	public void deleteByCartid(String cartid) {
		cartItemRepository.deleteByCartid(cartid);
	}

	@Override
	public void addOrUpdateCartItems(List<CartItem> cartItems) {
		cartItemRepository.save(cartItems);
	}

}
