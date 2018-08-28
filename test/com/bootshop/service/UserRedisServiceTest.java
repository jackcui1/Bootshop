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

    @Test
    public void addItem() {

        User user = userService.findByUsername("test");
        Product p1 = productService.getProductById(3);
        Product p2 = productService.getProductById(4);
        Product p3 = productService.getProductById(5);
        p1.getCatetory().setSubCategories(null);
        p2.getCatetory().setSubCategories(null);
        p3.getCatetory().setSubCategories(null);
        itemViewRedisService.add(user, p1);
        itemViewRedisService.add(user, p2);
        itemViewRedisService.add(user, p3);
        itemViewRedisService.add(user, p1);
        //itemViewRedisService.add(user, p2);
        System.out.println("count...." + itemViewRedisService.count(user));
        Set<Product> ps = itemViewRedisService.findAll(user);
        ps.forEach(p -> {
            System.out.println(p);
        });
       // System.out.println(user.toString());
    }
}
