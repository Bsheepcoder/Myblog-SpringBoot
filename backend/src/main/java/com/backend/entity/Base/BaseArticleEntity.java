package com.backend.entity.Base;

import com.backend.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseArticleEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  文章ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    String articleid;

    /**
     *  用户ID
     */
    @TableField(value = "accountid")
    String accountid;

    /**
     *  文章标题
     */
    @TableField(value = "title")
    String title;

    /**
     *  文章简介
     */
    @TableField(value = "overview")
    String overview;

    /**
     *  文章正文
     */
    @TableField(value = "content")
    String content;

    /**
     *  查看数
     */
    @TableField(value = "viewcount")
    Integer viewcount;

    /**
     *  喜欢数
     */
    @TableField(value = "likecount")
    Integer likecount;

    /**
     *  分享数
     */
    @TableField(value = "sharecount")
    Integer sharecount;
}
