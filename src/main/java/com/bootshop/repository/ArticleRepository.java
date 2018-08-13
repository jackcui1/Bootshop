package com.bootshop.repository;

import com.bootshop.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Guowei Cui
 * @date 8/13/2018 7:50 AM
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
