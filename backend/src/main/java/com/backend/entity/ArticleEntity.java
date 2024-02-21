package com.backend.entity;

import com.backend.common.entity.BaseEntity;
import com.backend.entity.Base.BaseAccountEntity;
import com.backend.entity.Base.BaseArticleEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author: qxd
 * @date: 2024/2/21
 * @description:
 */

@TableName("article")
public class ArticleEntity extends BaseArticleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return super.toString();
    }
}
