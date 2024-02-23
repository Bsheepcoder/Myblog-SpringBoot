package com.backend.service;

import com.backend.entity.AccountEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * @author: qxd
 * @date: 2024/2/23
 * @description:
 */
@Service
public interface AccountService  extends IService<AccountEntity> {

    AccountEntity findAccountByNameOrEmail(String username,String mail);
}
