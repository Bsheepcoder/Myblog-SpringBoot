package com.backend.service.Impl;

import com.backend.entity.Article;
import com.backend.entity.ArticleView;
import com.backend.mapper.ArticleMapper;
import com.backend.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl  implements ArticleService {

    @Resource
    ArticleMapper aritcleMapper;

    @Override
    public List<ArticleView> getArticleList() {
        return aritcleMapper.getArticleList();
    }

    @Override
    public boolean addArticle(String title, int tag, Date datetime, String overview, String text, int authorid) {
        return aritcleMapper.addArticle(title,tag,datetime,overview,text,authorid);
    }

    @Override
    public boolean deleteArticle(int aid) {
        return aritcleMapper.deleteArticle(aid);
    }

    @Override
    public boolean updateArticle(int aid, String title, int tag, Date datetime, String overview,String text) {
        return aritcleMapper.updateArticle(aid, title, tag, datetime, overview,text);
    }

    @Override
    public Article getArticle(int aid) {
        return aritcleMapper.getArticle(aid);
    }
}
