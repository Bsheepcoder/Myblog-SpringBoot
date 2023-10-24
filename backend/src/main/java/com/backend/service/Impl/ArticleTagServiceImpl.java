package com.backend.service.Impl;

import com.backend.mapper.ArticleTagMapper;
import com.backend.service.ArticleTagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Resource
    ArticleTagMapper articleTagMapper;

    @Override
    public boolean addArticleTag(int aid, int tid) {
        return articleTagMapper.addTagForArticle(aid,tid);
    }

    @Override
    public boolean deleteArticleTag(int aid) {
       return articleTagMapper.deleteArticleTag(aid);
    }
}
