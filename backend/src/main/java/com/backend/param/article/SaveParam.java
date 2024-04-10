package com.backend.param.article;

import com.backend.common.params.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */


@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "文章-修改保存参数",description = "文章-修改保存参数")
public class SaveParam extends Param {

    @ApiModelProperty(value = "文章ID")
    String articleid;

    @ApiModelProperty(value = "用户ID")
    String accountid;

    @ApiModelProperty(value = "标签ID")
    String tagid;

    @ApiModelProperty(value = "标题")
    String title;

    @ApiModelProperty(value = "概要")
    String overview;

    @ApiModelProperty(value = "正文")
    String content;

    @ApiModelProperty(value = "阅读量")
    Integer viewcount;

    @ApiModelProperty(value = "点赞量")
    Integer likecount;

    @ApiModelProperty(value = "转发量")
    Integer sharecount;
}
