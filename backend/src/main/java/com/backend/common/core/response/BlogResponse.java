package com.backend.common.core.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */
@ApiModel(value = "返回结果实体", description = "返回结果实体")
@Getter
@Setter
public class BlogResponse<T> {
    private static final long serialVersionUID = -8713837118340960775L;
    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";
    @ApiModelProperty(name = "code", value = "状态码 200成功")
    private int code = 200;
    @ApiModelProperty(name = "success", value = "数据交互成功标志 true:数据成功返回 false:数据请求失败")
    private boolean success = true;
    @ApiModelProperty(name = "msg", value = "消息内容")
    private String msg;
    @ApiModelProperty(name = "result", value = "数据对象")
    private T result;

    /**
     * 返回时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public BlogResponse() {
    }

    public BlogResponse(T result) {
        this.result = result;
    }

    public BlogResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BlogResponse(int code, boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    public BlogResponse(int code, boolean success, String msg, T result) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.result = result;
    }


    public static <T> BlogResponse<T> success() {
        return success("操作成功！");
    }

    public <T> BlogResponse<T> message(String message) {
        return success(message);
    }

    public static <T> BlogResponse<T> success(String msg) {
        return success(msg, (Object) null);
    }

    public static <T> BlogResponse<T> success(T data) {
        return new BlogResponse(200, true, "操作成功！", data);
    }

    public static <T> BlogResponse<T> success(String msg, Object data) {
        return new BlogResponse(200, true, msg, data);
    }

    public static BlogResponse error() {
        return error("操作失败");
    }

    public static BlogResponse error(String msg) {
        return error(msg, (Object) null);
    }

    public static BlogResponse error(String msg, Object data) {
        return new BlogResponse(500, false, msg, data);
    }

    public static BlogResponse error(int code, String msg) {
        return new BlogResponse(code, false, msg, (Object) null);
    }

}
