package com.bootshop.service;

import com.bootshop.model.Cart;
import com.bootshop.model.Customer;
import com.bootshop.model.ShippingAddress;
import com.bootshop.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Guowei Cui
 * @date 8/14/2018 2:32 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {


//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Autowired
//    private RedisTemplate redisTemplate;

    @Autowired
    private CustomerRedisService service;

    @Test
    public void testSave() throws Exception {

        // save a single Customer
        Cart cart = new Cart();
        ShippingAddress sa = new ShippingAddress();
        User user = new User();
        Customer customer = new Customer(22,"Jack","cuigw@gmail.com","61212", sa,cart,user);
        Customer customer2 = new Customer(21,"Jack","cuigw@gmail.com","61212", sa,cart,user);

        service.save(customer);


    }


    @Test
    public void testFind() {
        Customer customer = service.find(22);
        System.out.println(customer.toString());

    }

    @Test
    public void testFindAll() {
        Map<Integer,Customer> map = service.findAll();
        System.out.println(map.toString());
    }

    @Test
    public void testDel() {
        service.delete(23);
    }


    @Test
    public void testupdate() {
        Customer customer = service.find(22);
        customer.setCustomername("LiMing");
        service.update(customer);
    }
    @Test
    public void testObj() throws Exception {
//        User1 user=new User1("aa@126.com", "aa", "aa123456", "aa","123");
//        ValueOperations<String, User1> operations=redisTemplate.opsForValue();
//        operations.set("com.neox", user);
//        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
//        Thread.sleep(1000);
//        //redisTemplate.delete("com.neo.f");
//        boolean exists=redisTemplate.hasKey("com.neo.f");
//        if(exists){
//            System.out.println("exists is true");
//        }else{
//            System.out.println("exists is false");
//        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }

}

class User1 {
    private String email;
    private String name;
    private String password;
    private String desc;
    private String age;

    public User1(String email, String name, String password, String desc, String age) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.desc = desc;
        this.age = age;
    }
}