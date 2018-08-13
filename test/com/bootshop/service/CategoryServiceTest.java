package com.bootshop.service;

import com.bootshop.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(CategoryServiceTest.class);

    @Autowired
    CategoryService service;

    @Autowired
    ProductService productService;

    @Test
    public void findAll() {

        List<Category> lists = service.findAll();
        logger.info(lists.toString());

    }

}

