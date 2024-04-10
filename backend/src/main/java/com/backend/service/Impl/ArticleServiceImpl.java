package com.backend.service.Impl;

import com.backend.entity.ArticleEntity;
import com.backend.mapper.ArticleMapper;
import com.backend.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ArticleEntity addAriticle(ArticleEntity articleEntity) {
        this.baseMapper.insert(articleEntity);
        return articleEntity;
    }

    @Override
    public List<ArticleEntity> getTagArticle(String tagid) {
        return articleMapper.getTagArticle(tagid);
    }
}
