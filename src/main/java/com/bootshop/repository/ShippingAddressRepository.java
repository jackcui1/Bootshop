package com.bootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootshop.model.ShippingAddress;

public interface ShippingAddressRepository  extends JpaRepository<ShippingAddress, Integer>{

}
