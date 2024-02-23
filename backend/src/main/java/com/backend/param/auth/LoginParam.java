package com.backend.param.auth;

import com.backend.common.core.params.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: qxd
 * @date: 2024/2/23
 * @description:
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "登录参数")
public class LoginParam extends Param {
    @ApiModelProperty(value = "用户名")
    String username;

    @ApiModelProperty(value = "密码")
    String password;

    @ApiModelProperty(value = "验证码")
    String code;
}
