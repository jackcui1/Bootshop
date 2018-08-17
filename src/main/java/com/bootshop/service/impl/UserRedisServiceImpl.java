package com.bootshop.service.impl;

import com.bootshop.common.CommonConstants;
import com.bootshop.model.Customer;
import com.bootshop.model.User;
import com.bootshop.service.UserRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/17/2018 12:22 PM
 */

@Service
public class UserRedisServiceImpl implements UserRedisService {

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Integer, User> hashOperations;

    @Autowired
    public UserRedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void add(User user) {
        hashOperations.put(CommonConstants.KEY_LOGIN_USER, user.getUserid(), user);
    }

    @Override
    public List<User> findAll(List<User> users) {
        List<Integer> ids = new ArrayList<>();
        users.forEach(
                (user) -> {
                    ids.add(user.getUserid());
                }
        );
        return hashOperations.multiGet(CommonConstants.KEY_LOGIN_USER,ids);
    }

    @Override
    public User find(int id) {
        return hashOperations.get(CommonConstants.KEY_LOGIN_USER, id);
    }
}
