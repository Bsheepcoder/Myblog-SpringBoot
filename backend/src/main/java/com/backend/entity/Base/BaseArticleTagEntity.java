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
public class BaseArticleTagEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     *  文章标签ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    String articletagid;


    /**
     *  文章ID
     */
    @TableField(value = "articleid")
    String articleid;


    /**
     *  标签ID
     */
    @TableField(value = "tagid")
    String tagid;
}
