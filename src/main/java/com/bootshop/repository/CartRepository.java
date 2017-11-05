package com.bootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootshop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, String>{
}
