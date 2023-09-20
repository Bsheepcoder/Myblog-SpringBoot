package com.backend.service;


import com.backend.entity.Article;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ArticleService {

    List<Article> getArticleList();
    void addArticle(String title, int tag, Date datetime, String overview, String text, int autherid);
}
