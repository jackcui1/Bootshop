package com.bootshop.flow;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bootshop.model.Customer;
import com.bootshop.service.CustomerService;

@Component
public class OrderFlowAction implements Serializable{
	

	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(OrderFlowAction.class.getName());
	
	@Autowired
	CustomerService customerService;
	
	public Customer lookupCustomer() throws CustomerNotFoundException{
		  String username=SecurityContextHolder.getContext().getAuthentication().getName();
	      Customer customer = customerService.getCustomerBycustomername(username);
	      if(customer != null) {
	    	//log.info("customer: "+customer);
	        return customer;
	      } else {
	        throw new CustomerNotFoundException("Customer not found.");
	      }
	}
	
}
