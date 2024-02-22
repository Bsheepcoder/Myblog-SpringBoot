package com.backend.config.comm.mybatis;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

public class CustomWrapper extends MapWrapper {


    public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }


    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        name =  useCamelCaseMapping && !StringUtils.isCamel(name) ? StringUtils.underlineToCamel(name) : name;
        return name == null ? "" : name;
    }
}
