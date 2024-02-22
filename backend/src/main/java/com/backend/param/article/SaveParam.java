package com.backend.param.article;

import com.backend.common.core.params.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */


@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "文章查询参数",description = "文章查询参数")
public class SaveParam extends Param {

    @ApiModelProperty(value = "用户ID")
    Integer aid;

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
