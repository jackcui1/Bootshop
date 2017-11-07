package com.bootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootshop.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Customer getCustomerBycustomername(String customername);
	
	@Query(value="select * from customer c where c.cartid=?1",nativeQuery = true)
	Customer findOneByCartid(String cartid);
}
