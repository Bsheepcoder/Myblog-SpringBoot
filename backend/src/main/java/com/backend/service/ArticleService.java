package com.backend.service;


import com.backend.entity.ArticleEntity;
import com.backend.entity.Base.BaseArticleEntity;
import com.backend.mapper.ArticleMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService  extends IService<ArticleEntity> {

}
