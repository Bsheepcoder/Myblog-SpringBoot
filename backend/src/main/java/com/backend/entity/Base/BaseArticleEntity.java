package com.backend.entity.Base;

import com.backend.common.core.entity.BaseEntity;
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

    @TableId(type = IdType.ASSIGN_ID)
    BigDecimal articleid;

    @TableField(value = "aid")
    BigDecimal aid;

    @TableField(value = "title")
    String title;

    @TableField(value = "overview")
    String overview;

    @TableField(value = "content")
    String content;

    @TableField(value = "viewcount")
    Integer viewcount;

    @TableField(value = "likecount")
    Integer likecount;

    @TableField(value = "sharecount")
    Integer sharecount;
}
