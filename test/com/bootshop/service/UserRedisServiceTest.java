package com.bootshop.service;

import com.bootshop.model.Product;
import com.bootshop.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Guowei Cui
 * @date 8/17/2018 10:44 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRedisServiceTest {

    @Autowired
    private UserRedisService userRedisService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemViewRedisService itemViewRedisService;

    @Autowired
    private ProductService productService;

    @Test
    public void test() {

        User user = userRedisService.find(8);
        System.out.println(user.toString());
    }


    @Test
    public void testFindAll() {
        List<User> users = userService.findAll();
        Map<Integer, User> maps = userRedisService.findAll();
        System.out.println(maps.get(8).toString());
    }

}
