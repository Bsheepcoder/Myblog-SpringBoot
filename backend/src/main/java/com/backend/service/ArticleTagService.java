package com.backend.service;

import org.springframework.stereotype.Service;

@Service
public interface ArticleTagService {
    public boolean addArticleTag(int aid,int tid);
    public boolean deleteArticleTag(int aid);
}
