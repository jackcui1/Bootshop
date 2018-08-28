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
import java.util.Map;

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


    /**
     * Record loged in user.
     * data structure -- map
     * key -- loginMap
     * value -- k:token v:user
     */

    @Override
    public void add(User user) {
        long timestamp = System.currentTimeMillis() / 1000;
        //redisTemplate.opsForHash().put(CommonConstants.KEY_LOGIN_USER, user.getUserid(), user);
        redisTemplate.boundZSetOps(CommonConstants.KEY_LOGIN_USER).add(user.getUserid(), timestamp);

    }

    @Override
    public Map<Integer, User> findAll() {

        return hashOperations.entries(CommonConstants.KEY_LOGIN_USER);
    }

    @Override
    public User find(int id) {
        return hashOperations.get(CommonConstants.KEY_LOGIN_USER, id);
    }
}
