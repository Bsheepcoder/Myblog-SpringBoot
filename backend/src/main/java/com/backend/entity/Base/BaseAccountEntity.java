package com.backend.entity.Base;

import com.backend.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: qxd
 * @date: 2024/2/21
 * @description:
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseAccountEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    BigDecimal id;

    @TableField(value = "username")
    String username;

    @TableField(value = "password")
    String password;

    @TableField(value = "mail")
    String mail;
}
