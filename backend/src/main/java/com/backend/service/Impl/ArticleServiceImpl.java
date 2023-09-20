package com.backend.service.Impl;

import com.backend.entity.Article;
import com.backend.mapper.AritcleMapper;
import com.backend.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl  implements ArticleService {

    @Resource
    AritcleMapper aritcleMapper;

    @Override
    public List<Article> getArticleList() {
        return aritcleMapper.getArticleList();
    }

    @Override
    public void addArticle(String title, int tag, Date datetime, String overview, String text, int autherid) {
        aritcleMapper.addArticle(title,tag,datetime,overview,text,autherid);
        return ;
    }
}
