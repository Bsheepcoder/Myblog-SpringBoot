package com.backend.service.Impl;

import com.backend.entity.Article;
import com.backend.entity.ArticleView;
import com.backend.mapper.ArticleMapper;
import com.backend.service.ArticleService;
import com.backend.service.ArticleTagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl  implements ArticleService {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    ArticleTagService articleTagService;

    @Override
    public List<ArticleView> getArticleList() {
        return articleMapper.getArticleList();
    }

    @Override
    public List<ArticleView> getArticleList(int tid) {
        return articleMapper.getArticleListByTid(tid);
    }

    //这里应使用事务进行改写
    @Override
    public boolean addArticle(String title,Date datetime, String overview, String text,int tid) {
        //创建新Id
        int newId = getArticleNum() + 1;
        return articleMapper.addArticle(newId,title,datetime,datetime,overview,text,0) &&  articleTagService.addArticleTag(newId,tid);
    }

    @Override
    public boolean deleteArticle(int aid) {
        articleTagService.deleteArticleTag(aid);
        return articleMapper.deleteArticle(aid);
    }

    @Override
    public boolean updateArticle(String title,Date datetime,String overview,String text,int aid) {
        return articleMapper.updateArticle(title,datetime, overview,text,aid);
    }

    @Override
    public Article getArticle(int aid) {
        return articleMapper.getArticle(aid);
    }

    @Override
    public int getArticleNum() {
        return articleMapper.getArticleNum();
    }
}
