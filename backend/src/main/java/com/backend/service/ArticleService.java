package com.backend.service;


import com.backend.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {

    List<Article> getArticleList();
    void addArticle(Article article);
}
