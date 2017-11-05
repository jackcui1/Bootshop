package com.bootshop.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bootshop.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
	@Modifying
    @Transactional
	@Query(value="delete from cartitem where productid=?1",nativeQuery = true)
	void deleteByProductid(int productid);
	
	@Modifying
    @Transactional
	@Query(value="delete from cartitem where cartid=?1",nativeQuery = true)
	void deleteByCartid(String cartid);

}
