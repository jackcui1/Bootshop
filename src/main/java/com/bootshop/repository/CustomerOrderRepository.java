package com.bootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bootshop.model.Customer;
import com.bootshop.model.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer>{
	@Query(value="select * from customerorder c where c.customerid=?1",nativeQuery = true)
	CustomerOrder findByCustomerid(int customerid);
}
