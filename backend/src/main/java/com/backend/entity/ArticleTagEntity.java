package com.backend.entity;

import com.backend.entity.Base.BaseArticleTagEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author: qxd
 * @date: 2024/2/21
 * @description:
 */

@TableName("article_tag")
public class ArticleTagEntity extends BaseArticleTagEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }
}
