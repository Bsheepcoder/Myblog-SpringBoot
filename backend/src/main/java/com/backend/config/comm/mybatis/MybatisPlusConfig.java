package com.backend.config.comm.mybatis;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 单数据源配置（casit.datasource.open = false时生效）
 */
@Configuration
@MapperScan(value = {"com.backend.**.mapper*"})
public class MybatisPlusConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return i -> i.setObjectWrapperFactory(new CustomMapWrapperFactory());
    }

}
