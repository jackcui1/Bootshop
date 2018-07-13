package com.bootshop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootshop.model.Customer;
import com.bootshop.repository.CustomerRepository;
import com.bootshop.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public void addCustomer(Customer customer) {
		
		customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(int id) {
		return  customerRepository.getOne(id);
	}

	@Override
	public List<Customer> getCustomerAll() {
		
		return customerRepository.findAll();
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
		
	}

	@Override
	public void editCustomer(Customer customer) {
		customerRepository.save(customer);
		
	}

	@Override
	public Customer getCustomerBycustomername(String customername) {
		return customerRepository.getCustomerBycustomername(customername);
	}

//	@Override
//	public boolean findCustomerByCart(Cart cart) {
//		return customerRepository.findCustomerByCart(cart);
//	}

}
