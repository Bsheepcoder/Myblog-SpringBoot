package com.backend.param.auth;

import com.backend.common.params.Param;
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

    @ApiModelProperty(value = "用户名",required = true)
    String username;

    @ApiModelProperty(value = "密码",required = true)
    String password;

    @ApiModelProperty(value = "记住",required = true)
    Boolean remember;

    @ApiModelProperty(value = "验证码",required = true)
    String imageCode;
}
