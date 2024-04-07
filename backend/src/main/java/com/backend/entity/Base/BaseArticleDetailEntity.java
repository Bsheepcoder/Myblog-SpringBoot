package com.backend.entity.Base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import com.backend.common.entity.BaseEntity;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: qxd
 * @date: 2024/2/21
 * @description:
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseArticleDetailEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户详情ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    String accountdetialid;


    /**
     * 用户ID
     */
    @TableField(value = "accountid")
    String accountid;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    String nickname;

    /**
     * 电话
     */
    @TableField(value = "phonenumber")
    String phonenumber;

    /**
     * 照片
     */
    @TableField(value = "headpicture")
    String headpicture;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    Date birthday;

    /**
     * 签名
     */
    @TableField(value = "signature")
    String signature;


}
