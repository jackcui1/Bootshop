package com.bootshop.controller.restapi;

import com.bootshop.model.Article;
import com.bootshop.model.Category;
import com.bootshop.model.Product;
import com.bootshop.service.ArticleService;
import com.bootshop.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Guowei Cui
 * @date 2018/11/14 15:40
 */

@RestController
@RequestMapping("/rest/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService service;

    @RequestMapping("/{id}")
    public Article getById(@PathVariable("id") Integer id, Model model) {

        List<Article> articles = service.findByType(id);
        Article article = articles.get(0);

        return article;
    }

    @RequestMapping("/images/{id}")
    public List<Article> getImageById(@PathVariable("id") Integer id, Model model) {

        List<Article> articles = service.findByType(id);
        for (Article article : articles) {
            article.setAbsolutImagename(ImageUtils.imageNameToAbsolutePath(article.getImagename()));
        }

        return articles;
    }

}
