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
public class BaseTagEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *  标签ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    String tagid;

    /**
     *  父级标签ID
     */
    @TableField(value = "parent")
    String parent;

    /**
     *  层级
     */
    @TableField(value = "levels")
    String levels;

    /**
     * 标签名
     */
    @TableField(value = "tagname")
    String tagname;
}
