package com.backend.common.core.entity;

import java.util.regex.Pattern;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */

public interface RegexpConstant {
    String MOBILE = "[1]\\d{10}";
    Pattern CHINESE = Pattern.compile("[一-龥]");
}
