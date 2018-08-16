package com.bootshop.service.impl;

import com.bootshop.model.Customer;
import com.bootshop.service.CustomerRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author Guowei Cui
 * @date 8/14/2018 3:04 PM
 */

@Service
public class CustomerRedisServiceImpl implements CustomerRedisService {

    private static final String KEY = "Customer";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Integer, Customer> hashOperations;

    @Autowired
    public CustomerRedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void save(Customer customer) {
        hashOperations.put(KEY, customer.getCustomerid(), customer);
    }

    @Override
    public Customer find(Integer id) {
        return hashOperations.get(KEY, id);
    }

    @Override
    public Map<Integer, Customer> findAll() {
        return hashOperations.entries(KEY);
    }

    @Override
    public void update(Customer customer) {
        hashOperations.put(KEY, customer.getCustomerid(), customer);
    }

    @Override
    public void delete(Integer id) {
        hashOperations.delete(KEY, id);
    }
}
