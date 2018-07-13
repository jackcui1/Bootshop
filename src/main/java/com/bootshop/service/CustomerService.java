package com.bootshop.service;

import java.util.List;

import com.bootshop.model.Cart;
import com.bootshop.model.Customer;

public interface CustomerService {
	void addCustomer(Customer customer);
	Customer getCustomerById(int id);
	Customer getCustomerBycustomername(String customername);
	//boolean findCustomerByCart(Cart cart);
	List<Customer> getCustomerAll();
	void deleteCustomer(Customer customer);
	void editCustomer(Customer customer);
}
