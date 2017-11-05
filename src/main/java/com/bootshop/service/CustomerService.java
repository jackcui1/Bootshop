package com.bootshop.service;

import java.util.List;

import com.bootshop.model.Cart;
import com.bootshop.model.Customer;

public interface CustomerService {
	void addCustomer(Customer customer,Cart cart);
	void addCustomer(Customer customer);
	Customer getCustomerById(int id);
	Customer getCustomerBycustomername(String customername);
	Customer findCustomerByCartid(String cartid);
	List<Customer> getCustomerAll();
	void deleteCustomer(Customer customer);
	void editCustomer(Customer customer);
}
