package com.backend.config.comm.mybatis;

import com.backend.common.utils.mybatis.IdGeneratorUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * create by: dingwj
 * description: 自定义主键生成策略
 * create time: 13:36 2022/5/27
 *
 * @Param: null
 * @return
 */
@Slf4j
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public BigInteger nextId(Object entity) {
        BigInteger UUId = new BigInteger(IdGeneratorUtil.getSysNewId());
        return UUId;
    }

}
