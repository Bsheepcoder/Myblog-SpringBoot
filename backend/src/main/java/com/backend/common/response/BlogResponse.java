package com.backend.common.response;

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
    public static final String message_TAG = "message";
    public static final String DATA_TAG = "data";
    @ApiModelProperty(name = "code", value = "状态码 200成功")
    private int code = 200;
    @ApiModelProperty(name = "success", value = "数据交互成功标志 true:数据成功返回 false:数据请求失败")
    private boolean success = true;
    @ApiModelProperty(name = "message", value = "消息内容")
    private String message;
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

    public BlogResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public BlogResponse(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public BlogResponse(int code, boolean success, String message, T result) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.result = result;
    }


    public static <T> BlogResponse<T> success() {
        return success("操作成功！");
    }

    public <T> BlogResponse<T> message(String message) {
        return success(message);
    }

    public static <T> BlogResponse<T> success(String message) {
        return success(message, (Object) null);
    }

    public static <T> BlogResponse<T> success(T data) {
        return new BlogResponse(200, true, "操作成功！", data);
    }

    public static <T> BlogResponse<T> success(String message, Object data) {
        return new BlogResponse(200, true, message, data);
    }

    public static BlogResponse error() {
        return error("操作失败");
    }

    public static BlogResponse error(String message) {
        return error(message, (Object) null);
    }

    public static BlogResponse error(String message, Object data) {
        return new BlogResponse(500, false, message, data);
    }

    public static BlogResponse error(int code, String message) {
        return new BlogResponse(code, false, message, (Object) null);
    }

}
