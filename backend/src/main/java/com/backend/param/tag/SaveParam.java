package com.backend.param.tag;

import com.backend.common.params.Param;
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


    @ApiModelProperty(value = "父级ID")
    String parent;

    @ApiModelProperty(value = "层级")
    String levels;

    @ApiModelProperty(value = "标签名")
    String tagname;

}
