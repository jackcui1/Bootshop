package com.bootshop.service;

import com.bootshop.model.Article;

import java.util.List;

/**
 * @author Guowei Cui
 * @date 8/13/2018 7:51 AM
 */
public interface ArticleService {
    public Article findById(Integer id);
    public List<Article> findByType(int type);
    public void delete(Article article);
    public void deleteById(Integer id);
    public Article save(Article article);

}
