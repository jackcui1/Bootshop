package com.bootshop.service.impl;

import com.bootshop.common.CommonConstants;
import com.bootshop.model.Product;
import com.bootshop.model.User;
import com.bootshop.service.ItemViewRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Set;

/**
 * Save record of product which recent user browsing
 * <p>
 * Data structure -- zset
 * key -- viewZset:token
 * value -- v:item score:timestamp
 *
 * @author Guowei Cui
 * @date 8/18/2018 8:19 PM
 */
@Service
public class ItemViewRedisServiceImpl implements ItemViewRedisService {

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, Integer, User> hashOperations;

    @Autowired
    public ItemViewRedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public BoundZSetOperations getBoundZSetOperations(User user) {
        return redisTemplate.boundZSetOps(CommonConstants.KEY_USER_VIEW_PREFIX + user.getUserid());
    }

    @Override
    public void add(User user, Product product) {
        long timestamp = System.currentTimeMillis() / 1000;
        BoundZSetOperations boundZSetOperations = this.getBoundZSetOperations(user);
        boundZSetOperations.add(product, timestamp);
        boundZSetOperations.remove(0, -26);
        boundZSetOperations.incrementScore(product, -1);
    }

    @Override
    public Long count(User user) {
        BoundZSetOperations boundZSetOperations = this.getBoundZSetOperations(user);
        return boundZSetOperations.size();
    }

    @Override
    public Set<Product> findAll(User user) {
        BoundZSetOperations boundZSetOperations = this.getBoundZSetOperations(user);
        long count = boundZSetOperations.size();
        if (count > 0) {
            return boundZSetOperations.range(1,2);
        }else {
            return null;
        }
    }

    @Override
    public void removeAll(User user) {
        BoundZSetOperations boundZSetOperations = this.getBoundZSetOperations(user);
        boundZSetOperations.removeRange(1, 26);

    }
}
