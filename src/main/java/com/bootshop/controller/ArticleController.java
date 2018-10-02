package com.bootshop.controller;

import com.bootshop.model.Article;
import com.bootshop.model.Category;
import com.bootshop.service.ArticleService;
import com.bootshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/13/2018 7:59 AM
 */

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService service;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/type/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {

        List<Article> articles = service.findByType(id);
        Article article = articles.get(0);

        if (article != null ) {
            model.addAttribute("article",article);
        } else {
            model.addAttribute("msg","Can not find any article by Id: "+ id);
            return "403";
        }
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "articles";
    }



}
