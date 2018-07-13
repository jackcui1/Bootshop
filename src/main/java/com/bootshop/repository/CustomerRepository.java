package com.bootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootshop.model.Cart;
import com.bootshop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer getCustomerBycustomername(String customername);
	//boolean findCustomerByCart(Cart cart);
}
