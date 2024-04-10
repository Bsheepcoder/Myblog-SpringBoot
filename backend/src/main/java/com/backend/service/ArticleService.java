package com.backend.service;


import com.backend.entity.ArticleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService  extends IService<ArticleEntity> {

    public ArticleEntity addAriticle(ArticleEntity articleEntity);

    public List<ArticleEntity> getTagArticle(String tagid);
}
