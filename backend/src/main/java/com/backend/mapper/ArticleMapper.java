package com.backend.mapper;


import com.backend.entity.ArticleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;

/**
 * @author: qxd
 * @date: 2024/2/21
 * @description:
 */

public interface ArticleMapper extends BaseMapper<ArticleEntity> {

    public List<ArticleEntity> getTagArticle(String tagid);
}
