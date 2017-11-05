package com.bootshop.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootshop.model.ShippingAddress;
import com.bootshop.repository.ShippingAddressRepository;
import com.bootshop.service.ShippingAddressService;

@Service
@Transactional
public class ShippingAddressServiceImpl implements ShippingAddressService{
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	
	@Override
	public ShippingAddress addAddress(ShippingAddress shippingAddress) {
		return shippingAddressRepository.save(shippingAddress);
	}
	
	
}
