package com.bootshop.service;

import com.bootshop.model.User;

import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/17/2018 12:19 PM
 */
public interface UserRedisService {
    void add(User user);
    List<User> findAll(List<User> users);
    User find(int id);
}
