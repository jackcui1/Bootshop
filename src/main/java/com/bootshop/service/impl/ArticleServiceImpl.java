package com.bootshop.service.impl;

import com.bootshop.model.Article;
import com.bootshop.repository.ArticleRepository;
import com.bootshop.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Guowei Cui
 * @date 8/13/2018 7:53 AM
 */

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Override
    public Article findById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(Article article) {
        repository.delete(article);
    }

    @Override
    public void deleteById(Integer id) {
        repository.delete(id);
    }

    @Override
    public Article save(Article article) {
        return repository.save(article);
    }
}
