package com.bootshop.service;

import com.bootshop.model.User;

import java.util.List;

public interface UserService {
	void save(User user);
	User findByUsername(String username);
	List<User> findAll();
}
