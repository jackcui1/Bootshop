package com.bootshop.service.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootshop.model.CustomerOrder;
import com.bootshop.repository.CustomerOrderRepository;
import com.bootshop.service.CustomerOrderService;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService{
	
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	@Override
	public void addCustomerOrder(CustomerOrder customerOrder) {
		customerOrderRepository.saveAndFlush(customerOrder);
	}

	@Override
	public CustomerOrder findByCustomerid(int customerid) {
		return customerOrderRepository.findByCustomerid(customerid);
		
	}

	public CustomerOrder validate(int orderid)throws IOException{
			CustomerOrder order=findById(orderid);
			
			if(order==null ){
				throw new IOException(orderid+"");
			}
			return order;
	}

	@Override
	public CustomerOrder findById(int id) {
		
		return customerOrderRepository.findOne(id);
	}
}
