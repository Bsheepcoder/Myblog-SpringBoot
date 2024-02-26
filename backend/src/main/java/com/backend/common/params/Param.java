package com.backend.common.params;

import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */
public class Param implements Serializable {
    public Param() {
    }

    public <T> T convert(T bean) {
        BeanUtils.copyProperties(this, bean);
        return bean;
    }
}
