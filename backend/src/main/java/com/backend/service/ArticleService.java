package com.backend.service;


import com.backend.entity.Article;
import com.backend.entity.ArticleView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public interface ArticleService {

    List<ArticleView> getArticleList();

    @Transactional
    public boolean addArticle(String title,Date datetime, String overview, String text,int tid);

    public boolean deleteArticle(int aid);

    public boolean updateArticle(String title,Date datetime,String overview,String text,int aid);

    public Article getArticle(int aid);

    //获取文章数
    public int getArticleNum();
}
