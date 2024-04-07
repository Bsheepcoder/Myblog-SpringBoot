package com.backend.entity.Base;

import com.backend.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: qxd
 * @date: 2024/2/21
 * @description:
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseAccountEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    String accountid;

    /**
     * 用户名
     */
    @TableField(value = "username")
    String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    String password;

    /**
     * 邮箱
     */
    @TableField(value = "mail")
    String mail;
}
