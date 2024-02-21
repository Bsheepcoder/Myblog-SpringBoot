package com.backend.mapper;

import com.backend.entity.AccountEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author: qxd
 * @date: 2024/2/21
 * @description:
 */

public interface AccountMapper extends BaseMapper<AccountEntity> {

    AccountEntity findAccountByNameOrEmail(String username,String mail);

    Boolean resetPasswordByEmail(String password,String mail);

    Boolean createAccount(String username,String password,String mail);
}
