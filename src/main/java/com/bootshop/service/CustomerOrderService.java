package com.bootshop.service;

import com.bootshop.model.CustomerOrder;

public interface CustomerOrderService {
	public void addCustomerOrder(CustomerOrder customerOrder);
	public CustomerOrder findByCustomerid(int customerid);
	public CustomerOrder findById(int id);
}
