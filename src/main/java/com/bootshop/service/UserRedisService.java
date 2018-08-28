package com.bootshop.service;

import com.bootshop.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author Guowei Cui
 * @date 8/17/2018 12:19 PM
 */
public interface UserRedisService {
    void add(User user);
    Map<Integer, User> findAll();
    User find(int id);
}
