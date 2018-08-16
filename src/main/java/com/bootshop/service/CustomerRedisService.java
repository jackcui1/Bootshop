package com.bootshop.service;

import com.bootshop.model.Customer;

import java.util.Map;

/**
 * @author Guowei Cui
 * @date 8/14/2018 3:02 PM
 */
public interface CustomerRedisService {

    void save(Customer customer);
    Customer find(Integer id);
    Map<Integer, Customer> findAll();
    void update(Customer customer);
    void delete(Integer id);
}
