package com.backend.service;


import com.backend.entity.Article;
import com.backend.entity.ArticleView;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ArticleService {

    List<ArticleView> getArticleList();
    public boolean addArticle(String title, int tag, Date datetime, String overview, String text, int authorid);
}
