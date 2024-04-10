package com.backend.param.article;

import com.backend.common.params.Param;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: qxd
 * @date: 2024/4/10
 * @description:
 */

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "文章标签-修改保存参数",description = "文章-修改保存参数")
public class SaveArticleTagParam extends Param {

    @ApiModelProperty(value = "文章标签ID")
    String articletagid;

    @ApiModelProperty(value = "文章ID")
    String articleid;

    @ApiModelProperty(value = "标签ID")
    String tagid;
}
