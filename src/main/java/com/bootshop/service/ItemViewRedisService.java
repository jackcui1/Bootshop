package com.bootshop.service;

import com.bootshop.model.Product;
import com.bootshop.model.User;

import java.util.Set;

/**
 * @author Guowei Cui
 * @date 8/18/2018 8:17 PM
 */
public interface ItemViewRedisService {

    void add(User user, Product product);

    Long count(User user);

    Set<Product> findAll(User user);

    void removeAll(User user);
}
